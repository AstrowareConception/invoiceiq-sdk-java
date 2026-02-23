package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Représente les moyens de paiement.
 */
public class PaymentMeans {
    @SerializedName("typeCode")
    private String typeCode;

    @SerializedName("accountIBAN")
    private String accountIBAN;

    @SerializedName("accountBIC")
    private String accountBIC;

    @SerializedName("accountName")
    private String accountName;

    public PaymentMeans() {
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getAccountIBAN() {
        return accountIBAN;
    }

    public void setAccountIBAN(String accountIBAN) {
        this.accountIBAN = accountIBAN;
    }

    public String getAccountBIC() {
        return accountBIC;
    }

    public void setAccountBIC(String accountBIC) {
        this.accountBIC = accountBIC;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String typeCode;
        private String accountIBAN;
        private String accountBIC;
        private String accountName;

        public Builder typeCode(String typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        public Builder accountIBAN(String accountIBAN) {
            this.accountIBAN = accountIBAN;
            return this;
        }

        public Builder accountBIC(String accountBIC) {
            this.accountBIC = accountBIC;
            return this;
        }

        public Builder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public PaymentMeans build() {
            PaymentMeans paymentMeans = new PaymentMeans();
            paymentMeans.setTypeCode(typeCode);
            paymentMeans.setAccountIBAN(accountIBAN);
            paymentMeans.setAccountBIC(accountBIC);
            paymentMeans.setAccountName(accountName);
            return paymentMeans;
        }
    }
}
