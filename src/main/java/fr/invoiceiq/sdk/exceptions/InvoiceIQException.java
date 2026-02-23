package fr.invoiceiq.sdk.exceptions;

/**
 * Exception de base pour toutes les erreurs du SDK InvoiceIQ.
 */
public class InvoiceIQException extends Exception {
    private final int statusCode;

    public InvoiceIQException(String message) {
        super(message);
        this.statusCode = -1;
    }

    public InvoiceIQException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = -1;
    }

    public InvoiceIQException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public InvoiceIQException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean hasStatusCode() {
        return statusCode > 0;
    }
}
