package fr.invoiceiq.sdk.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.invoiceiq.sdk.config.InvoiceIQConfig;
import fr.invoiceiq.sdk.exceptions.*;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Client HTTP de base pour communiquer avec l'API InvoiceIQ.
 */
public class BaseHttpClient {
    private static final Logger logger = LoggerFactory.getLogger(BaseHttpClient.class);
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    protected final InvoiceIQConfig config;
    protected final OkHttpClient httpClient;
    protected final Gson gson;

    public BaseHttpClient(InvoiceIQConfig config) {
        this.config = config;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(config.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(config.getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(config.getWriteTimeout(), TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    /**
     * Construit une requête HTTP avec les headers d'authentification appropriés.
     */
    protected Request.Builder buildRequest(String url) {
        Request.Builder builder = new Request.Builder().url(url);

        if (config.hasApiKey()) {
            builder.addHeader("X-API-KEY", config.getApiKey());
        } else if (config.hasBearerToken()) {
            builder.addHeader("Authorization", "Bearer " + config.getBearerToken());
        }

        return builder;
    }

    /**
     * Exécute une requête HTTP et gère les erreurs.
     */
    protected Response executeRequest(Request request) throws InvoiceIQException {
        try {
            Response response = httpClient.newCall(request).execute();
            handleErrorResponse(response);
            return response;
        } catch (IOException e) {
            throw new InvoiceIQException("Network error: " + e.getMessage(), e);
        }
    }

    /**
     * Gère les réponses d'erreur de l'API.
     */
    protected void handleErrorResponse(Response response) throws InvoiceIQException {
        if (response.isSuccessful()) {
            return;
        }

        int statusCode = response.code();
        String errorMessage;

        try {
            ResponseBody body = response.body();
            errorMessage = body != null ? body.string() : "Unknown error";
        } catch (IOException e) {
            errorMessage = "Failed to read error response";
        }

        switch (statusCode) {
            case 400:
                throw new ValidationException("Invalid request: " + errorMessage);
            case 401:
                throw new AuthenticationException("Authentication failed: " + errorMessage);
            case 402:
                int creditsRequired = extractCreditsRequired(errorMessage);
                throw new InsufficientCreditsException("Insufficient credits: " + errorMessage, creditsRequired);
            case 403:
                throw new InvoiceIQException("Forbidden: " + errorMessage, statusCode);
            case 404:
                throw new InvoiceIQException("Resource not found: " + errorMessage, statusCode);
            case 429:
                throw new InvoiceIQException("Rate limit exceeded: " + errorMessage, statusCode);
            default:
                throw new InvoiceIQException("API error (HTTP " + statusCode + "): " + errorMessage, statusCode);
        }
    }

    /**
     * Extrait le nombre de crédits requis du message d'erreur.
     */
    private int extractCreditsRequired(String errorMessage) {
        // Essayer d'extraire le nombre de crédits du message
        // Par exemple: "Crédits insuffisants (5 requis)"
        try {
            if (errorMessage.contains("(") && errorMessage.contains("requis")) {
                String[] parts = errorMessage.split("\\(");
                if (parts.length > 1) {
                    String creditsPart = parts[1].split(" ")[0];
                    return Integer.parseInt(creditsPart);
                }
            }
        } catch (Exception e) {
            // Ignore parsing errors
        }
        return 0;
    }

    /**
     * Convertit un objet en JSON.
     */
    protected String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * Parse une réponse JSON en objet.
     */
    protected <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * Intercepteur pour logger les requêtes/réponses.
     */
    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            logger.debug("Request: {} {}", request.method(), request.url());

            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(request);
            long duration = System.currentTimeMillis() - startTime;

            logger.debug("Response: {} {} ({}ms)", response.code(), request.url(), duration);

            return response;
        }
    }
}
