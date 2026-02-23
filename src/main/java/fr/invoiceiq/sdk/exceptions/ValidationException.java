package fr.invoiceiq.sdk.exceptions;

/**
 * Exception levée quand les données fournies sont invalides.
 */
public class ValidationException extends InvoiceIQException {
    public ValidationException(String message) {
        super(message, 400);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, 400, cause);
    }
}
