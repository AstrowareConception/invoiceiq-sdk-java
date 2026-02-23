package fr.invoiceiq.sdk.client;

import fr.invoiceiq.sdk.config.InvoiceIQConfig;
import fr.invoiceiq.sdk.exceptions.InvoiceIQException;
import fr.invoiceiq.sdk.models.ValidationResult;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * Client pour les opérations de validation de documents Factur-X.
 */
public class ValidationClient extends BaseHttpClient {

    public ValidationClient(InvoiceIQConfig config) {
        super(config);
    }

    /**
     * Valide un document Factur-X ou PDF.
     * Coût: 1 crédit.
     *
     * @param file Le fichier PDF à valider
     * @return Le résultat de la validation
     * @throws InvoiceIQException En cas d'erreur
     */
    public ValidationResult validate(File file) throws InvoiceIQException {
        String url = config.getBaseUrl() + "/v1/validations";

        RequestBody fileBody = RequestBody.create(file, MediaType.parse("application/pdf"));
        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .build();

        Request request = buildRequest(url)
                .post(requestBody)
                .build();

        try (Response response = executeRequest(request)) {
            String responseBody = response.body().string();
            return fromJson(responseBody, ValidationResult.class);
        } catch (IOException e) {
            throw new InvoiceIQException("Failed to read validation response", e);
        }
    }

    /**
     * Crée une validation gratuite (sans authentification, avec limite de débit).
     *
     * @param file Le fichier PDF à valider
     * @return Le résultat de la validation
     * @throws InvoiceIQException En cas d'erreur
     */
    public ValidationResult validateFree(File file) throws InvoiceIQException {
        String url = config.getBaseUrl() + "/v1/free-validations";

        RequestBody fileBody = RequestBody.create(file, MediaType.parse("application/pdf"));
        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .build();

        // Ne pas ajouter les headers d'authentification pour la validation gratuite
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = executeRequest(request)) {
            String responseBody = response.body().string();
            return fromJson(responseBody, ValidationResult.class);
        } catch (IOException e) {
            throw new InvoiceIQException("Failed to read validation response", e);
        }
    }

    /**
     * Récupère une validation par son ID.
     *
     * @param validationId L'ID de la validation
     * @return Le résultat de la validation
     * @throws InvoiceIQException En cas d'erreur
     */
    public ValidationResult getValidation(String validationId) throws InvoiceIQException {
        String url = config.getBaseUrl() + "/v1/validations/" + validationId;

        Request request = buildRequest(url)
                .get()
                .build();

        try (Response response = executeRequest(request)) {
            String responseBody = response.body().string();
            return fromJson(responseBody, ValidationResult.class);
        } catch (IOException e) {
            throw new InvoiceIQException("Failed to read validation response", e);
        }
    }
}
