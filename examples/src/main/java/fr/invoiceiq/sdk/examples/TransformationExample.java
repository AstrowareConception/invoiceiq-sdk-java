package fr.invoiceiq.sdk.examples;

import fr.invoiceiq.sdk.InvoiceIQClient;
import fr.invoiceiq.sdk.exceptions.InvoiceIQException;
import fr.invoiceiq.sdk.models.*;

import java.io.File;

/**
 * Exemple de transformation d'un PDF simple en Factur-X.
 */
public class TransformationExample {

    public static void main(String[] args) {
        // Configuration du client
        InvoiceIQClient client = InvoiceIQClient.builder()
                .apiKey("your-api-key-here")
                .build();

        try {
            // Création des métadonnées de transformation
            TransformationMetadata metadata = createTransformationMetadata();

            // Transformation du PDF
            File sourcePdf = new File("path/to/source.pdf");
            Job job = client.transformation().transform(sourcePdf, metadata);

            System.out.println("Transformation job created: " + job.getId());
            System.out.println("Status: " + job.getStatus());

            // Attendre la complétion (polling toutes les 2 secondes, timeout de 60 secondes)
            Job completedJob = client.transformation().waitForCompletion(
                    job.getId(),
                    2000,  // 2 secondes entre chaque poll
                    60000  // timeout de 60 secondes
            );

            if (completedJob.isCompleted()) {
                System.out.println("Transformation completed!");

                // Télécharger le résultat
                File outputFile = new File("path/to/output-facturx.pdf");
                client.transformation().downloadResult(completedJob, outputFile);
                System.out.println("Factur-X PDF downloaded to: " + outputFile.getAbsolutePath());

                // Télécharger le rapport
                TransformationReport report = client.transformation().downloadReport(completedJob);
                System.out.println("\nTransformation Report:");
                System.out.println("  Status: " + report.getTransformation());
                System.out.println("  Final Score: " + report.getFinalScore());
                System.out.println("  Profile: " + report.getProfile());

                if (report.hasIssues()) {
                    System.out.println("  Issues:");
                    for (TransformationReport.ReportIssue issue : report.getIssues()) {
                        System.out.println("    - " + issue.getMessage() + " (Code: " + issue.getCode() + ")");
                    }
                }
            } else if (completedJob.isFailed()) {
                System.err.println("Transformation failed: " + completedJob.getError());
            }

        } catch (InvoiceIQException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static TransformationMetadata createTransformationMetadata() {
        return TransformationMetadata.builder()
                .invoiceNumber("INV-2024-001")
                .issueDate("2024-02-22")
                .currency("EUR")
                .seller(Party.builder()
                        .name("My Company SAS")
                        .registrationId("12345678900012")
                        .vatId("FR12123456789")
                        .countryCode("FR")
                        .address(Address.builder()
                                .line1("10 Rue de la Paix")
                                .city("Paris")
                                .postCode("75001")
                                .countryCode("FR")
                                .build())
                        .build())
                .buyer(Party.builder()
                        .name("Customer Company")
                        .countryCode("FR")
                        .address(Address.builder()
                                .line1("5 Avenue des Champs")
                                .city("Paris")
                                .postCode("75008")
                                .countryCode("FR")
                                .build())
                        .build())
                .addLine(TransformationInvoiceLine.builder()
                        .id("1")
                        .name("Consulting Service")
                        .quantity(5)
                        .unitCode("HUR")
                        .netPrice(100.00)
                        .taxRate(20.00)
                        .totalAmount(500.00)
                        .build())
                .addTax(TransformationTaxSummary.builder()
                        .taxRate(20.00)
                        .basisAmount(500.00)
                        .taxAmount(100.00)
                        .build())
                .totalTaxExclusiveAmount(500.00)
                .taxTotalAmount(100.00)
                .totalTaxInclusiveAmount(600.00)
                .build();
    }
}
