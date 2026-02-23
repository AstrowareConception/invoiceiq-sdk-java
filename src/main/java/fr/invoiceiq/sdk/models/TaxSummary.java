package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

/**
 * Représente un récapitulatif de taxe.
 */
public class TaxSummary {
    @SerializedName("taxRate")
    private BigDecimal taxRate;

    @SerializedName("basisAmount")
    private BigDecimal basisAmount;

    @SerializedName("taxableAmount")
    private BigDecimal taxableAmount;

    @SerializedName("taxAmount")
    private BigDecimal taxAmount;

    @SerializedName("taxCategoryCode")
    private String taxCategoryCode = "S";

    @SerializedName("taxExemptionReason")
    private String taxExemptionReason;

    public TaxSummary() {
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getBasisAmount() {
        return basisAmount;
    }

    public void setBasisAmount(BigDecimal basisAmount) {
        this.basisAmount = basisAmount;
    }

    public BigDecimal getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(BigDecimal taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTaxCategoryCode() {
        return taxCategoryCode;
    }

    public void setTaxCategoryCode(String taxCategoryCode) {
        this.taxCategoryCode = taxCategoryCode;
    }

    public String getTaxExemptionReason() {
        return taxExemptionReason;
    }

    public void setTaxExemptionReason(String taxExemptionReason) {
        this.taxExemptionReason = taxExemptionReason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BigDecimal taxRate;
        private BigDecimal basisAmount;
        private BigDecimal taxableAmount;
        private BigDecimal taxAmount;
        private String taxCategoryCode = "S";
        private String taxExemptionReason;

        public Builder taxRate(BigDecimal taxRate) {
            this.taxRate = taxRate;
            return this;
        }

        public Builder taxRate(double taxRate) {
            this.taxRate = BigDecimal.valueOf(taxRate);
            return this;
        }

        public Builder basisAmount(BigDecimal basisAmount) {
            this.basisAmount = basisAmount;
            return this;
        }

        public Builder basisAmount(double basisAmount) {
            this.basisAmount = BigDecimal.valueOf(basisAmount);
            return this;
        }

        public Builder taxableAmount(BigDecimal taxableAmount) {
            this.taxableAmount = taxableAmount;
            return this;
        }

        public Builder taxableAmount(double taxableAmount) {
            this.taxableAmount = BigDecimal.valueOf(taxableAmount);
            return this;
        }

        public Builder taxAmount(BigDecimal taxAmount) {
            this.taxAmount = taxAmount;
            return this;
        }

        public Builder taxAmount(double taxAmount) {
            this.taxAmount = BigDecimal.valueOf(taxAmount);
            return this;
        }

        public Builder taxCategoryCode(String taxCategoryCode) {
            this.taxCategoryCode = taxCategoryCode;
            return this;
        }

        public Builder taxExemptionReason(String taxExemptionReason) {
            this.taxExemptionReason = taxExemptionReason;
            return this;
        }

        public TaxSummary build() {
            TaxSummary summary = new TaxSummary();
            summary.setTaxRate(taxRate);
            summary.setBasisAmount(basisAmount);
            summary.setTaxableAmount(taxableAmount);
            summary.setTaxAmount(taxAmount);
            summary.setTaxCategoryCode(taxCategoryCode);
            summary.setTaxExemptionReason(taxExemptionReason);
            return summary;
        }
    }
}
