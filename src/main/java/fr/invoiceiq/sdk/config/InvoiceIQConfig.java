package fr.invoiceiq.sdk.config;

/**
 * Configuration du client InvoiceIQ.
 */
public class InvoiceIQConfig {
    private final String apiKey;
    private final String bearerToken;
    private final String baseUrl;
    private final int connectTimeout;
    private final int readTimeout;
    private final int writeTimeout;

    private InvoiceIQConfig(Builder builder) {
        this.apiKey = builder.apiKey;
        this.bearerToken = builder.bearerToken;
        this.baseUrl = builder.baseUrl;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public boolean hasApiKey() {
        return apiKey != null && !apiKey.isEmpty();
    }

    public boolean hasBearerToken() {
        return bearerToken != null && !bearerToken.isEmpty();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String apiKey;
        private String bearerToken;
        private String baseUrl = "https://api.invoiceiq.fr";
        private int connectTimeout = 30;
        private int readTimeout = 60;
        private int writeTimeout = 60;

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder bearerToken(String bearerToken) {
            this.bearerToken = bearerToken;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder connectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder readTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder writeTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public InvoiceIQConfig build() {
            if (apiKey == null && bearerToken == null) {
                throw new IllegalArgumentException("Either apiKey or bearerToken must be provided");
            }
            return new InvoiceIQConfig(this);
        }
    }
}
