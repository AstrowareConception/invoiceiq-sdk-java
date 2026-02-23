package fr.invoiceiq.sdk;

import fr.invoiceiq.sdk.models.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests pour les builders d'Invoice.
 */
class InvoiceBuilderTest {

    @Test
    void testInvoiceBuilder() {
        Invoice invoice = Invoice.builder()
                .invoiceNumber("INV-2024-001")
                .issueDate("2024-02-22")
                .currency("EUR")
                .seller(Party.builder()
                        .name("Test Company")
                        .vatId("FR123456789")
                        .countryCode("FR")
                        .build())
                .buyer(Party.builder()
                        .name("Customer Company")
                        .countryCode("FR")
                        .build())
                .addLine(InvoiceLine.builder()
                        .id("1")
                        .name("Product A")
                        .quantity(1)
                        .unitPrice(100.00)
                        .taxRate(20.00)
                        .totalAmount(100.00)
                        .build())
                .totalTaxExclusiveAmount(100.00)
                .taxTotalAmount(20.00)
                .totalTaxInclusiveAmount(120.00)
                .build();

        assertNotNull(invoice);
        assertEquals("INV-2024-001", invoice.getInvoiceNumber());
        assertEquals("2024-02-22", invoice.getIssueDate());
        assertEquals("EUR", invoice.getCurrency());
        assertEquals("Test Company", invoice.getSeller().getName());
        assertEquals("Customer Company", invoice.getBuyer().getName());
        assertEquals(1, invoice.getLines().size());
        assertEquals(0, new BigDecimal("100.00").compareTo(invoice.getTotalTaxExclusiveAmount()));
        assertEquals(0, new BigDecimal("20.00").compareTo(invoice.getTaxTotalAmount()));
        assertEquals(0, new BigDecimal("120.00").compareTo(invoice.getTotalTaxInclusiveAmount()));
    }

    @Test
    void testPartyBuilder() {
        Party party = Party.builder()
                .name("Company Name")
                .registrationId("12345678900012")
                .vatId("FR123456789")
                .countryCode("FR")
                .address(Address.builder()
                        .line1("123 Main St")
                        .city("Paris")
                        .postCode("75001")
                        .countryCode("FR")
                        .build())
                .email("contact@company.com")
                .build();

        assertNotNull(party);
        assertEquals("Company Name", party.getName());
        assertEquals("12345678900012", party.getRegistrationId());
        assertEquals("FR123456789", party.getVatId());
        assertEquals("FR", party.getCountryCode());
        assertEquals("contact@company.com", party.getEmail());
        assertNotNull(party.getAddress());
        assertEquals("123 Main St", party.getAddress().getLine1());
    }

    @Test
    void testInvoiceLineBuilder() {
        InvoiceLine line = InvoiceLine.builder()
                .id("1")
                .name("Service A")
                .description("Description of service A")
                .quantity(2.5)
                .unitCode("HUR")
                .unitPrice(50.00)
                .taxRate(20.00)
                .totalAmount(125.00)
                .build();

        assertNotNull(line);
        assertEquals("1", line.getId());
        assertEquals("Service A", line.getName());
        assertEquals("Description of service A", line.getDescription());
        assertEquals(new BigDecimal("2.5"), line.getQuantity());
        assertEquals("HUR", line.getUnitCode());
        assertEquals(0, new BigDecimal("50.00").compareTo(line.getUnitPrice()));
        assertEquals(0, new BigDecimal("20.00").compareTo(line.getTaxRate()));
        assertEquals(0, new BigDecimal("125.00").compareTo(line.getTotalAmount()));
    }

    @Test
    void testTaxExemptionLine() {
        InvoiceLine line = InvoiceLine.builder()
                .id("1")
                .name("Exempt Service")
                .quantity(1)
                .unitPrice(100.00)
                .taxRate(0.00)
                .taxCategoryCode("E")
                .taxExemptionReason("TVA non applicable, art. 293 B du CGI")
                .totalAmount(100.00)
                .build();

        assertNotNull(line);
        assertEquals("E", line.getTaxCategoryCode());
        assertEquals("TVA non applicable, art. 293 B du CGI", line.getTaxExemptionReason());
        assertEquals(0, new BigDecimal("0.00").compareTo(line.getTaxRate()));
    }

    @Test
    void testRenderingOptionsBuilder() {
        RenderingOptions rendering = RenderingOptions.builder()
                .template("classic-01")
                .font("Helvetica")
                .primaryColor("#0F172A")
                .accentColor("#2563EB")
                .logo(RenderingOptions.Logo.builder()
                        .url("https://example.com/logo.png")
                        .width(150)
                        .align("left")
                        .build())
                .footer(RenderingOptions.Footer.builder()
                        .extraText("Company SAS - SIRET 123456789")
                        .showPageNumbers(true)
                        .build())
                .locale("fr-FR")
                .notes("Thank you for your business")
                .build();

        assertNotNull(rendering);
        assertEquals("classic-01", rendering.getTemplate());
        assertEquals("Helvetica", rendering.getFont());
        assertEquals("#0F172A", rendering.getPrimaryColor());
        assertEquals("#2563EB", rendering.getAccentColor());
        assertNotNull(rendering.getLogo());
        assertEquals("https://example.com/logo.png", rendering.getLogo().getUrl());
        assertEquals(150, rendering.getLogo().getWidth());
        assertNotNull(rendering.getFooter());
        assertEquals("Company SAS - SIRET 123456789", rendering.getFooter().getExtraText());
        assertTrue(rendering.getFooter().getShowPageNumbers());
    }

    @Test
    void testPaymentMeansBuilder() {
        PaymentMeans payment = PaymentMeans.builder()
                .typeCode("30")
                .accountIBAN("FR7630006000011234567890123")
                .accountBIC("AGRIFRPP")
                .accountName("Company SAS")
                .build();

        assertNotNull(payment);
        assertEquals("30", payment.getTypeCode());
        assertEquals("FR7630006000011234567890123", payment.getAccountIBAN());
        assertEquals("AGRIFRPP", payment.getAccountBIC());
        assertEquals("Company SAS", payment.getAccountName());
    }
}
