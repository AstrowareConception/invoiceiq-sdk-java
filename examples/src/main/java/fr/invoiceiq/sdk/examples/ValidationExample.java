package fr.invoiceiq.sdk.examples;

import fr.invoiceiq.sdk.InvoiceIQClient;
import fr.invoiceiq.sdk.exceptions.InvoiceIQException;
import fr.invoiceiq.sdk.models.ValidationResult;

import java.io.File;

/**
 * Exemple d'utilisation du client de validation.
 */
public class ValidationExample {

    public static void main(String[] args) {
        // Configuration du client avec API Key
        InvoiceIQClient client = InvoiceIQClient.builder()
                .apiKey("your-api-key-here")
                .build();

        try {
            // Validation d'un document Factur-X
            File pdfFile = new File("path/to/your/invoice.pdf");
            ValidationResult result = client.validation().validate(pdfFile);

            System.out.println("Validation ID: " + result.getId());
            System.out.println("Is Valid: " + result.isValid());
            System.out.println("Score: " + result.getScore());
            System.out.println("Profile: " + result.getProfile());

            if (result.hasIssues()) {
                System.out.println("\nIssues found:");
                for (ValidationResult.ValidationIssue issue : result.getIssues()) {
                    System.out.println("  - " + issue.getMessage());
                    System.out.println("    Code: " + issue.getCode());
                    System.out.println("    Severity: " + issue.getSeverity());
                }
            } else {
                System.out.println("\nNo issues found. Document is fully compliant!");
            }

        } catch (InvoiceIQException e) {
            System.err.println("Error: " + e.getMessage());
            if (e.hasStatusCode()) {
                System.err.println("Status Code: " + e.getStatusCode());
            }
            e.printStackTrace();
        }
    }
}
