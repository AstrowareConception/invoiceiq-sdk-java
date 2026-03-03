package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Payload pour la génération complète de Factur-X.
 * Utilisé pour le endpoint POST /api/v1/generations.
 */
public class GenerationPayload {
    @SerializedName("invoiceNumber")
    private String invoiceNumber;

    @SerializedName("issueDate")
    private String issueDate;

    @SerializedName("dueDate")
    private String dueDate;

    @SerializedName("currency")
    private String currency = "EUR";

    @SerializedName("templateId")
    private String templateId;

    @SerializedName("seller")
    private PartyDetail seller;

    @SerializedName("buyer")
    private PartyDetail buyer;

    @SerializedName("lines")
    private List<GenerationInvoiceLine> lines = new ArrayList<>();

    @SerializedName("totalTaxExclusiveAmount")
    private BigDecimal totalTaxExclusiveAmount;

    @SerializedName("taxTotalAmount")
    private BigDecimal taxTotalAmount;

    @SerializedName("totalTaxInclusiveAmount")
    private BigDecimal totalTaxInclusiveAmount;

    @SerializedName("taxSummaries")
    private List<GenerationTaxSummary> taxSummaries = new ArrayList<>();

    @SerializedName("notes")
    private String notes;

    @SerializedName("paymentMeans")
    private PaymentMeans paymentMeans;

    @SerializedName("rendering")
    private RenderingOptions rendering;

    public GenerationPayload() {
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

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public PartyDetail getSeller() {
        return seller;
    }

    public void setSeller(PartyDetail seller) {
        this.seller = seller;
    }

    public PartyDetail getBuyer() {
        return buyer;
    }

    public void setBuyer(PartyDetail buyer) {
        this.buyer = buyer;
    }

    public List<GenerationInvoiceLine> getLines() {
        return lines;
    }

    public void setLines(List<GenerationInvoiceLine> lines) {
        this.lines = lines;
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

    public List<GenerationTaxSummary> getTaxSummaries() {
        return taxSummaries;
    }

    public void setTaxSummaries(List<GenerationTaxSummary> taxSummaries) {
        this.taxSummaries = taxSummaries;
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
        private String templateId;
        private PartyDetail seller;
        private PartyDetail buyer;
        private List<GenerationInvoiceLine> lines = new ArrayList<>();
        private BigDecimal totalTaxExclusiveAmount;
        private BigDecimal taxTotalAmount;
        private BigDecimal totalTaxInclusiveAmount;
        private List<GenerationTaxSummary> taxSummaries = new ArrayList<>();
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

        public Builder templateId(String templateId) {
            this.templateId = templateId;
            return this;
        }

        public Builder seller(PartyDetail seller) {
            this.seller = seller;
            return this;
        }

        public Builder buyer(PartyDetail buyer) {
            this.buyer = buyer;
            return this;
        }

        public Builder addLine(GenerationInvoiceLine line) {
            this.lines.add(line);
            return this;
        }

        public Builder lines(List<GenerationInvoiceLine> lines) {
            this.lines = lines;
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

        public Builder addTaxSummary(GenerationTaxSummary taxSummary) {
            this.taxSummaries.add(taxSummary);
            return this;
        }

        public Builder taxSummaries(List<GenerationTaxSummary> taxSummaries) {
            this.taxSummaries = taxSummaries;
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

        public GenerationPayload build() {
            GenerationPayload payload = new GenerationPayload();
            payload.setInvoiceNumber(invoiceNumber);
            payload.setIssueDate(issueDate);
            payload.setDueDate(dueDate);
            payload.setCurrency(currency);
            payload.setTemplateId(templateId);
            payload.setSeller(seller);
            payload.setBuyer(buyer);
            payload.setLines(lines);
            payload.setTotalTaxExclusiveAmount(totalTaxExclusiveAmount);
            payload.setTaxTotalAmount(taxTotalAmount);
            payload.setTotalTaxInclusiveAmount(totalTaxInclusiveAmount);
            payload.setTaxSummaries(taxSummaries);
            payload.setNotes(notes);
            payload.setPaymentMeans(paymentMeans);
            payload.setRendering(rendering);
            return payload;
        }
    }
}
