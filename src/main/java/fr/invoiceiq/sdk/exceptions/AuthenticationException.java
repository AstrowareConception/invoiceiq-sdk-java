package fr.invoiceiq.sdk.exceptions;

/**
 * Exception levée en cas d'erreur d'authentification.
 */
public class AuthenticationException extends InvoiceIQException {
    public AuthenticationException(String message) {
        super(message, 401);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, 401, cause);
    }
}
