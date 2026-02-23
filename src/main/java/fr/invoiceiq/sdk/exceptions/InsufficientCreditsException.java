package fr.invoiceiq.sdk.exceptions;

/**
 * Exception levée quand le compte n'a pas assez de crédits.
 */
public class InsufficientCreditsException extends InvoiceIQException {
    private final int creditsRequired;

    public InsufficientCreditsException(String message, int creditsRequired) {
        super(message, 402);
        this.creditsRequired = creditsRequired;
    }

    public int getCreditsRequired() {
        return creditsRequired;
    }
}
