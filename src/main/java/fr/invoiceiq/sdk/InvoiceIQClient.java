package fr.invoiceiq.sdk;

import fr.invoiceiq.sdk.client.GenerationClient;
import fr.invoiceiq.sdk.client.TransformationClient;
import fr.invoiceiq.sdk.client.ValidationClient;
import fr.invoiceiq.sdk.config.InvoiceIQConfig;

/**
 * Client principal pour l'API InvoiceIQ.
 * <p>
 * Exemple d'utilisation avec API Key:
 * <pre>
 * InvoiceIQClient client = InvoiceIQClient.builder()
 *     .apiKey("your-api-key")
 *     .build();
 * </pre>
 * <p>
 * Exemple d'utilisation avec Bearer Token:
 * <pre>
 * InvoiceIQClient client = InvoiceIQClient.builder()
 *     .bearerToken("your-bearer-token")
 *     .build();
 * </pre>
 */
public class InvoiceIQClient {
    private final InvoiceIQConfig config;
    private final ValidationClient validationClient;
    private final TransformationClient transformationClient;
    private final GenerationClient generationClient;

    private InvoiceIQClient(InvoiceIQConfig config) {
        this.config = config;
        this.validationClient = new ValidationClient(config);
        this.transformationClient = new TransformationClient(config);
        this.generationClient = new GenerationClient(config);
    }

    /**
     * Accède aux opérations de validation.
     */
    public ValidationClient validation() {
        return validationClient;
    }

    /**
     * Accède aux opérations de transformation.
     */
    public TransformationClient transformation() {
        return transformationClient;
    }

    /**
     * Accède aux opérations de génération.
     */
    public GenerationClient generation() {
        return generationClient;
    }

    /**
     * Retourne la configuration utilisée par ce client.
     */
    public InvoiceIQConfig getConfig() {
        return config;
    }

    /**
     * Crée un builder pour configurer le client.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pour créer une instance de InvoiceIQClient.
     */
    public static class Builder {
        private final InvoiceIQConfig.Builder configBuilder;

        private Builder() {
            this.configBuilder = InvoiceIQConfig.builder();
        }

        /**
         * Configure l'API Key pour l'authentification.
         */
        public Builder apiKey(String apiKey) {
            configBuilder.apiKey(apiKey);
            return this;
        }

        /**
         * Configure le Bearer Token pour l'authentification.
         */
        public Builder bearerToken(String bearerToken) {
            configBuilder.bearerToken(bearerToken);
            return this;
        }

        /**
         * Configure l'URL de base de l'API.
         * Par défaut: https://api.invoiceiq.fr
         */
        public Builder baseUrl(String baseUrl) {
            configBuilder.baseUrl(baseUrl);
            return this;
        }

        /**
         * Configure le timeout de connexion en secondes.
         * Par défaut: 30 secondes
         */
        public Builder connectTimeout(int connectTimeout) {
            configBuilder.connectTimeout(connectTimeout);
            return this;
        }

        /**
         * Configure le timeout de lecture en secondes.
         * Par défaut: 60 secondes
         */
        public Builder readTimeout(int readTimeout) {
            configBuilder.readTimeout(readTimeout);
            return this;
        }

        /**
         * Configure le timeout d'écriture en secondes.
         * Par défaut: 60 secondes
         */
        public Builder writeTimeout(int writeTimeout) {
            configBuilder.writeTimeout(writeTimeout);
            return this;
        }

        /**
         * Construit l'instance de InvoiceIQClient.
         */
        public InvoiceIQClient build() {
            InvoiceIQConfig config = configBuilder.build();
            return new InvoiceIQClient(config);
        }
    }
}
