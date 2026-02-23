package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente une facture complète.
 */
public class Invoice {
    @SerializedName("invoiceNumber")
    private String invoiceNumber;

    @SerializedName("issueDate")
    private String issueDate;

    @SerializedName("dueDate")
    private String dueDate;

    @SerializedName("currency")
    private String currency = "EUR";

    @SerializedName("typeCode")
    private String typeCode = "380";

    @SerializedName("seller")
    private Party seller;

    @SerializedName("buyer")
    private Party buyer;

    @SerializedName("lines")
    private List<InvoiceLine> lines = new ArrayList<>();

    @SerializedName("taxes")
    private List<TaxSummary> taxes = new ArrayList<>();

    @SerializedName("taxSummaries")
    private List<TaxSummary> taxSummaries = new ArrayList<>();

    @SerializedName("totalTaxExclusiveAmount")
    private BigDecimal totalTaxExclusiveAmount;

    @SerializedName("taxTotalAmount")
    private BigDecimal taxTotalAmount;

    @SerializedName("totalTaxInclusiveAmount")
    private BigDecimal totalTaxInclusiveAmount;

    @SerializedName("purchaseOrderReference")
    private String purchaseOrderReference;

    @SerializedName("notes")
    private String notes;

    @SerializedName("paymentMeans")
    private PaymentMeans paymentMeans;

    @SerializedName("rendering")
    private RenderingOptions rendering;

    public Invoice() {
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public void setLines(List<InvoiceLine> lines) {
        this.lines = lines;
    }

    public List<TaxSummary> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<TaxSummary> taxes) {
        this.taxes = taxes;
    }

    public List<TaxSummary> getTaxSummaries() {
        return taxSummaries;
    }

    public void setTaxSummaries(List<TaxSummary> taxSummaries) {
        this.taxSummaries = taxSummaries;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public PaymentMeans getPaymentMeans() {
        return paymentMeans;
    }

    public void setPaymentMeans(PaymentMeans paymentMeans) {
        this.paymentMeans = paymentMeans;
    }

    public RenderingOptions getRendering() {
        return rendering;
    }

    public void setRendering(RenderingOptions rendering) {
        this.rendering = rendering;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String invoiceNumber;
        private String issueDate;
        private String dueDate;
        private String currency = "EUR";
        private String typeCode = "380";
        private Party seller;
        private Party buyer;
        private List<InvoiceLine> lines = new ArrayList<>();
        private List<TaxSummary> taxes = new ArrayList<>();
        private List<TaxSummary> taxSummaries = new ArrayList<>();
        private BigDecimal totalTaxExclusiveAmount;
        private BigDecimal taxTotalAmount;
        private BigDecimal totalTaxInclusiveAmount;
        private String purchaseOrderReference;
        private String notes;
        private PaymentMeans paymentMeans;
        private RenderingOptions rendering;

        public Builder invoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            return this;
        }

        public Builder issueDate(String issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        public Builder dueDate(String dueDate) {
            this.dueDate = dueDate;
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

        public Builder addLine(InvoiceLine line) {
            this.lines.add(line);
            return this;
        }

        public Builder lines(List<InvoiceLine> lines) {
            this.lines = lines;
            return this;
        }

        public Builder addTax(TaxSummary tax) {
            this.taxes.add(tax);
            this.taxSummaries.add(tax);
            return this;
        }

        public Builder taxes(List<TaxSummary> taxes) {
            this.taxes = taxes;
            this.taxSummaries = taxes;
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

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder paymentMeans(PaymentMeans paymentMeans) {
            this.paymentMeans = paymentMeans;
            return this;
        }

        public Builder rendering(RenderingOptions rendering) {
            this.rendering = rendering;
            return this;
        }

        public Invoice build() {
            Invoice invoice = new Invoice();
            invoice.setInvoiceNumber(invoiceNumber);
            invoice.setIssueDate(issueDate);
            invoice.setDueDate(dueDate);
            invoice.setCurrency(currency);
            invoice.setTypeCode(typeCode);
            invoice.setSeller(seller);
            invoice.setBuyer(buyer);
            invoice.setLines(lines);
            invoice.setTaxes(taxes);
            invoice.setTaxSummaries(taxSummaries);
            invoice.setTotalTaxExclusiveAmount(totalTaxExclusiveAmount);
            invoice.setTaxTotalAmount(taxTotalAmount);
            invoice.setTotalTaxInclusiveAmount(totalTaxInclusiveAmount);
            invoice.setPurchaseOrderReference(purchaseOrderReference);
            invoice.setNotes(notes);
            invoice.setPaymentMeans(paymentMeans);
            invoice.setRendering(rendering);
            return invoice;
        }
    }
}
