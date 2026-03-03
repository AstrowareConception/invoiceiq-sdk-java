package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Métadonnées pour la transformation PDF vers Factur-X.
 * Utilisé pour le endpoint POST /api/v1/transformations.
 */
public class TransformationMetadata {
    @SerializedName("invoiceNumber")
    private String invoiceNumber;

    @SerializedName("issueDate")
    private String issueDate;

    @SerializedName("currency")
    private String currency = "EUR";

    @SerializedName("typeCode")
    private String typeCode = "380";

    @SerializedName("seller")
    private Party seller;

    @SerializedName("buyer")
    private Party buyer;

    @SerializedName("lines")
    private List<TransformationInvoiceLine> lines = new ArrayList<>();

    @SerializedName("taxes")
    private List<TransformationTaxSummary> taxes = new ArrayList<>();

    @SerializedName("totalTaxExclusiveAmount")
    private BigDecimal totalTaxExclusiveAmount;

    @SerializedName("taxTotalAmount")
    private BigDecimal taxTotalAmount;

    @SerializedName("totalTaxInclusiveAmount")
    private BigDecimal totalTaxInclusiveAmount;

    @SerializedName("purchaseOrderReference")
    private String purchaseOrderReference;

    public TransformationMetadata() {
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Party getSeller() {
        return seller;
    }

    public void setSeller(Party seller) {
        this.seller = seller;
    }

    public Party getBuyer() {
        return buyer;
    }

    public void setBuyer(Party buyer) {
        this.buyer = buyer;
    }

    public List<TransformationInvoiceLine> getLines() {
        return lines;
    }

    public void setLines(List<TransformationInvoiceLine> lines) {
        this.lines = lines;
    }

    public List<TransformationTaxSummary> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<TransformationTaxSummary> taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getTotalTaxExclusiveAmount() {
        return totalTaxExclusiveAmount;
    }

    public void setTotalTaxExclusiveAmount(BigDecimal totalTaxExclusiveAmount) {
        this.totalTaxExclusiveAmount = totalTaxExclusiveAmount;
    }

    public BigDecimal getTaxTotalAmount() {
        return taxTotalAmount;
    }

    public void setTaxTotalAmount(BigDecimal taxTotalAmount) {
        this.taxTotalAmount = taxTotalAmount;
    }

    public BigDecimal getTotalTaxInclusiveAmount() {
        return totalTaxInclusiveAmount;
    }

    public void setTotalTaxInclusiveAmount(BigDecimal totalTaxInclusiveAmount) {
        this.totalTaxInclusiveAmount = totalTaxInclusiveAmount;
    }

    public String getPurchaseOrderReference() {
        return purchaseOrderReference;
    }

    public void setPurchaseOrderReference(String purchaseOrderReference) {
        this.purchaseOrderReference = purchaseOrderReference;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String invoiceNumber;
        private String issueDate;
        private String currency = "EUR";
        private String typeCode = "380";
        private Party seller;
        private Party buyer;
        private List<TransformationInvoiceLine> lines = new ArrayList<>();
        private List<TransformationTaxSummary> taxes = new ArrayList<>();
        private BigDecimal totalTaxExclusiveAmount;
        private BigDecimal taxTotalAmount;
        private BigDecimal totalTaxInclusiveAmount;
        private String purchaseOrderReference;

        public Builder invoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            return this;
        }

        public Builder issueDate(String issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder typeCode(String typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        public Builder seller(Party seller) {
            this.seller = seller;
            return this;
        }

        public Builder buyer(Party buyer) {
            this.buyer = buyer;
            return this;
        }

        public Builder addLine(TransformationInvoiceLine line) {
            this.lines.add(line);
            return this;
        }

        public Builder lines(List<TransformationInvoiceLine> lines) {
            this.lines = lines;
            return this;
        }

        public Builder addTax(TransformationTaxSummary tax) {
            this.taxes.add(tax);
            return this;
        }

        public Builder taxes(List<TransformationTaxSummary> taxes) {
            this.taxes = taxes;
            return this;
        }

        public Builder totalTaxExclusiveAmount(BigDecimal totalTaxExclusiveAmount) {
            this.totalTaxExclusiveAmount = totalTaxExclusiveAmount;
            return this;
        }

        public Builder totalTaxExclusiveAmount(double totalTaxExclusiveAmount) {
            this.totalTaxExclusiveAmount = BigDecimal.valueOf(totalTaxExclusiveAmount);
            return this;
        }

        public Builder taxTotalAmount(BigDecimal taxTotalAmount) {
            this.taxTotalAmount = taxTotalAmount;
            return this;
        }

        public Builder taxTotalAmount(double taxTotalAmount) {
            this.taxTotalAmount = BigDecimal.valueOf(taxTotalAmount);
            return this;
        }

        public Builder totalTaxInclusiveAmount(BigDecimal totalTaxInclusiveAmount) {
            this.totalTaxInclusiveAmount = totalTaxInclusiveAmount;
            return this;
        }

        public Builder totalTaxInclusiveAmount(double totalTaxInclusiveAmount) {
            this.totalTaxInclusiveAmount = BigDecimal.valueOf(totalTaxInclusiveAmount);
            return this;
        }

        public Builder purchaseOrderReference(String purchaseOrderReference) {
            this.purchaseOrderReference = purchaseOrderReference;
            return this;
        }

        public TransformationMetadata build() {
            TransformationMetadata metadata = new TransformationMetadata();
            metadata.setInvoiceNumber(invoiceNumber);
            metadata.setIssueDate(issueDate);
            metadata.setCurrency(currency);
            metadata.setTypeCode(typeCode);
            metadata.setSeller(seller);
            metadata.setBuyer(buyer);
            metadata.setLines(lines);
            metadata.setTaxes(taxes);
            metadata.setTotalTaxExclusiveAmount(totalTaxExclusiveAmount);
            metadata.setTaxTotalAmount(taxTotalAmount);
            metadata.setTotalTaxInclusiveAmount(totalTaxInclusiveAmount);
            metadata.setPurchaseOrderReference(purchaseOrderReference);
            return metadata;
        }
    }
}
