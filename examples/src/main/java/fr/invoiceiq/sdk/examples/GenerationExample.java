package fr.invoiceiq.sdk.examples;

import fr.invoiceiq.sdk.InvoiceIQClient;
import fr.invoiceiq.sdk.exceptions.InvoiceIQException;
import fr.invoiceiq.sdk.models.*;

import java.io.File;

/**
 * Exemple de génération complète d'une facture Factur-X.
 */
public class GenerationExample {

    public static void main(String[] args) {
        // Configuration du client
        InvoiceIQClient client = InvoiceIQClient.builder()
                .apiKey("your-api-key-here")
                .build();

        try {
            // Création d'un payload de génération complet avec options de rendu
            GenerationPayload payload = createGenerationPayload();

            // Génération de la facture Factur-X
            Job job = client.generation().generate(payload);

            System.out.println("Generation job created: " + job.getId());
            System.out.println("Status: " + job.getStatus());

            // Attendre la complétion
            Job completedJob = client.generation().waitForCompletion(
                    job.getId(),
                    2000,  // 2 secondes entre chaque poll
                    120000 // timeout de 120 secondes (génération plus longue)
            );

            if (completedJob.isCompleted()) {
                System.out.println("Generation completed!");

                // Télécharger le PDF Factur-X généré
                File outputFile = new File("path/to/generated-invoice.pdf");
                client.generation().downloadResult(completedJob, outputFile);
                System.out.println("Generated Factur-X PDF saved to: " + outputFile.getAbsolutePath());

                // Télécharger le rapport
                TransformationReport report = client.generation().downloadReport(completedJob);
                System.out.println("\nGeneration Report:");
                System.out.println("  Status: " + report.getTransformation());
                System.out.println("  Final Score: " + report.getFinalScore());
                System.out.println("  Profile: " + report.getProfile());

            } else if (completedJob.isFailed()) {
                System.err.println("Generation failed: " + completedJob.getError());
            }

        } catch (InvoiceIQException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static GenerationPayload createGenerationPayload() {
        return GenerationPayload.builder()
                .invoiceNumber("F-2024-00125")
                .issueDate("2024-02-15")
                .dueDate("2024-03-15")
                .currency("EUR")
                .templateId("classic-01")
                .seller(PartyDetail.builder()
                        .name("CloudLabs Infrastructure SAS")
                        .registrationId("98765432100012")
                        .vatId("FR987654321")
                        .countryCode("FR")
                        .addressLine1("12 Avenue de l'Innovation")
                        .addressLine2("Immeuble Le Delta")
                        .postCode("75013")
                        .city("Paris")
                        .email("billing@cloudlabs.io")
                        .build())
                .buyer(PartyDetail.builder()
                        .name("Global Soft Corp")
                        .registrationId("12345678900056")
                        .vatId("FR123456789")
                        .countryCode("FR")
                        .addressLine1("500 Boulevard de la Croisette")
                        .postCode("06400")
                        .city("Cannes")
                        .email("finance@globalsoft.com")
                        .build())
                .addLine(GenerationInvoiceLine.builder()
                        .id("1")
                        .name("Abonnement Premium SaaS - Annuel")
                        .description("Accès plateforme CloudLabs pour 25 utilisateurs (Période: Mars 2024 - Février 2025)")
                        .quantity(1)
                        .unitCode("C62")
                        .unitPrice(4500.00)
                        .taxRate(20.0)
                        .totalAmount(4500.00)
                        .build())
                .addLine(GenerationInvoiceLine.builder()
                        .id("2")
                        .name("Crédits API Supplémentaires")
                        .description("Pack de 10 000 appels API valables 1 an")
                        .quantity(2)
                        .unitCode("C62")
                        .unitPrice(250.00)
                        .taxRate(20.0)
                        .totalAmount(500.00)
                        .build())
                .addLine(GenerationInvoiceLine.builder()
                        .id("3")
                        .name("Consulting Architecture Cloud")
                        .description("Audit de performance infrastructure (3 jours)")
                        .quantity(3)
                        .unitCode("DAY")
                        .unitPrice(950.00)
                        .taxRate(20.0)
                        .totalAmount(2850.00)
                        .build())
                .addTaxSummary(GenerationTaxSummary.builder()
                        .taxRate(20.0)
                        .taxableAmount(7850.00)
                        .taxAmount(1570.00)
                        .build())
                .totalTaxExclusiveAmount(7850.00)
                .taxTotalAmount(1570.00)
                .totalTaxInclusiveAmount(9420.00)
                .notes("Paiement par virement bancaire sous 30 jours. Merci de votre confiance.")
                .paymentMeans(PaymentMeans.builder()
                        .typeCode("30")
                        .accountIBAN("FR7630006000011234567890123")
                        .accountBIC("CLOUDLABS75")
                        .accountName("CloudLabs Infrastructure SAS")
                        .build())
                .rendering(RenderingOptions.builder()
                        .template("classic-01")
                        .primaryColor("#1e293b")
                        .accentColor("#3b82f6")
                        .logo(RenderingOptions.Logo.builder()
                                .url("https://invoiceiq.fr/assets/demo/logo-cloudlabs.png")
                                .width(150)
                                .align("left")
                                .build())
                        .footer(RenderingOptions.Footer.builder()
                                .extraText("CloudLabs Infrastructure SAS - Capital 50 000€ - SIREN 987 654 321 - RCS Paris")
                                .showPageNumbers(true)
                                .build())
                        .locale("fr-FR")
                        .build())
                .build();
    }
}
