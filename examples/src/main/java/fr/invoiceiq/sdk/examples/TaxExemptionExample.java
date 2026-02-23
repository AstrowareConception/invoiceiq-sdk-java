package fr.invoiceiq.sdk.examples;

import fr.invoiceiq.sdk.InvoiceIQClient;
import fr.invoiceiq.sdk.exceptions.InvoiceIQException;
import fr.invoiceiq.sdk.models.*;

import java.io.File;

/**
 * Exemple de génération d'une facture avec exonération de TVA.
 */
public class TaxExemptionExample {

    public static void main(String[] args) {
        // Configuration du client
        InvoiceIQClient client = InvoiceIQClient.builder()
                .apiKey("your-api-key-here")
                .build();

        try {
            // Création d'une facture avec exonération de TVA (micro-entrepreneur)
            Invoice invoice = createTaxExemptInvoice();

            // Génération de la facture
            Job job = client.generation().generate(invoice);

            System.out.println("Generation job created: " + job.getId());

            // Attendre la complétion
            Job completedJob = client.generation().waitForCompletion(job.getId(), 2000, 120000);

            if (completedJob.isCompleted()) {
                System.out.println("Generation completed!");

                File outputFile = new File("path/to/tax-exempt-invoice.pdf");
                client.generation().downloadResult(completedJob, outputFile);
                System.out.println("Tax-exempt invoice saved to: " + outputFile.getAbsolutePath());
            }

        } catch (InvoiceIQException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Invoice createTaxExemptInvoice() {
        return Invoice.builder()
                .invoiceNumber("F-2024-MICRO-01")
                .issueDate("2024-02-22")
                .dueDate("2024-03-22")
                .currency("EUR")
                .seller(Party.builder()
                        .name("Jean Dupont Consultant")
                        .registrationId("88990011223344")
                        .countryCode("FR")
                        .addressLine1("15 Rue de l'Artisanat")
                        .postCode("33000")
                        .city("Bordeaux")
                        .email("contact@jd-consulting.fr")
                        .build())
                .buyer(Party.builder()
                        .name("Association Culturelle Locale")
                        .countryCode("FR")
                        .addressLine1("Mairie de Bordeaux")
                        .postCode("33000")
                        .city("Bordeaux")
                        .build())
                .addLine(InvoiceLine.builder()
                        .id("1")
                        .name("Formation Gestion Projet")
                        .description("Session d'accompagnement pour les bénévoles")
                        .quantity(2)
                        .unitCode("DAY")
                        .unitPrice(450.00)
                        .taxRate(0.0)
                        .taxCategoryCode("E")
                        .taxExemptionReason("TVA non applicable, art. 293 B du CGI")
                        .totalAmount(900.00)
                        .build())
                .addTax(TaxSummary.builder()
                        .taxRate(0.0)
                        .taxableAmount(900.00)
                        .taxAmount(0.00)
                        .taxCategoryCode("E")
                        .taxExemptionReason("TVA non applicable, art. 293 B du CGI")
                        .build())
                .totalTaxExclusiveAmount(900.00)
                .taxTotalAmount(0.00)
                .totalTaxInclusiveAmount(900.00)
                .notes("Micro-entrepreneur : TVA non applicable, art. 293 B du CGI.")
                .paymentMeans(PaymentMeans.builder()
                        .typeCode("30")
                        .accountIBAN("FR7699988877766655544433322")
                        .accountBIC("MICROFR75")
                        .accountName("Jean Dupont")
                        .build())
                .rendering(RenderingOptions.builder()
                        .template("classic-01")
                        .primaryColor("#065f46")
                        .footer(RenderingOptions.Footer.builder()
                                .extraText("Jean Dupont EI - SIRET 889 900 112 23344 - Dispensé d'immatriculation au RCS")
                                .build())
                        .build())
                .build();
    }
}
