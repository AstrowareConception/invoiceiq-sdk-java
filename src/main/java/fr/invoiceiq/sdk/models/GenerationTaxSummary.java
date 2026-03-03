package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

/**
 * Récapitulatif de taxe pour la génération (utilise taxableAmount).
 */
public class GenerationTaxSummary {
    @SerializedName("taxRate")
    private BigDecimal taxRate;

    @SerializedName("taxableAmount")
    private BigDecimal taxableAmount;

    @SerializedName("taxAmount")
    private BigDecimal taxAmount;

    @SerializedName("taxCategoryCode")
    private String taxCategoryCode = "S";

    @SerializedName("taxExemptionReason")
    private String taxExemptionReason;

    public GenerationTaxSummary() {
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
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

        public GenerationTaxSummary build() {
            GenerationTaxSummary summary = new GenerationTaxSummary();
            summary.setTaxRate(taxRate);
            summary.setTaxableAmount(taxableAmount);
            summary.setTaxAmount(taxAmount);
            summary.setTaxCategoryCode(taxCategoryCode);
            summary.setTaxExemptionReason(taxExemptionReason);
            return summary;
        }
    }
}
