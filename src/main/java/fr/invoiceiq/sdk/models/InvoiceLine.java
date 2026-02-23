package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

/**
 * Représente une ligne de facture.
 */
public class InvoiceLine {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("quantity")
    private BigDecimal quantity;

    @SerializedName("unitCode")
    private String unitCode = "C62";

    @SerializedName("unitPrice")
    private BigDecimal unitPrice;

    @SerializedName("netPrice")
    private BigDecimal netPrice;

    @SerializedName("taxRate")
    private BigDecimal taxRate;

    @SerializedName("taxCategoryCode")
    private String taxCategoryCode = "S";

    @SerializedName("taxExemptionReason")
    private String taxExemptionReason;

    @SerializedName("totalAmount")
    private BigDecimal totalAmount;

    public InvoiceLine() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private String description;
        private BigDecimal quantity;
        private String unitCode = "C62";
        private BigDecimal unitPrice;
        private BigDecimal netPrice;
        private BigDecimal taxRate;
        private String taxCategoryCode = "S";
        private String taxExemptionReason;
        private BigDecimal totalAmount;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder quantity(BigDecimal quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder quantity(double quantity) {
            this.quantity = BigDecimal.valueOf(quantity);
            return this;
        }

        public Builder unitCode(String unitCode) {
            this.unitCode = unitCode;
            return this;
        }

        public Builder unitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder unitPrice(double unitPrice) {
            this.unitPrice = BigDecimal.valueOf(unitPrice);
            return this;
        }

        public Builder netPrice(BigDecimal netPrice) {
            this.netPrice = netPrice;
            return this;
        }

        public Builder netPrice(double netPrice) {
            this.netPrice = BigDecimal.valueOf(netPrice);
            return this;
        }

        public Builder taxRate(BigDecimal taxRate) {
            this.taxRate = taxRate;
            return this;
        }

        public Builder taxRate(double taxRate) {
            this.taxRate = BigDecimal.valueOf(taxRate);
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

        public Builder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder totalAmount(double totalAmount) {
            this.totalAmount = BigDecimal.valueOf(totalAmount);
            return this;
        }

        public InvoiceLine build() {
            InvoiceLine line = new InvoiceLine();
            line.setId(id);
            line.setName(name);
            line.setDescription(description);
            line.setQuantity(quantity);
            line.setUnitCode(unitCode);
            line.setUnitPrice(unitPrice);
            line.setNetPrice(netPrice);
            line.setTaxRate(taxRate);
            line.setTaxCategoryCode(taxCategoryCode);
            line.setTaxExemptionReason(taxExemptionReason);
            line.setTotalAmount(totalAmount);
            return line;
        }
    }
}
