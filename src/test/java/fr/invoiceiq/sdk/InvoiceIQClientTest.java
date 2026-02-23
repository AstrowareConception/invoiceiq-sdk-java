package fr.invoiceiq.sdk;

import fr.invoiceiq.sdk.config.InvoiceIQConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests pour InvoiceIQClient.
 */
class InvoiceIQClientTest {

    @Test
    void testClientBuilderWithApiKey() {
        InvoiceIQClient client = InvoiceIQClient.builder()
                .apiKey("test-api-key")
                .build();

        assertNotNull(client);
        assertNotNull(client.validation());
        assertNotNull(client.transformation());
        assertNotNull(client.generation());

        InvoiceIQConfig config = client.getConfig();
        assertTrue(config.hasApiKey());
        assertEquals("test-api-key", config.getApiKey());
        assertFalse(config.hasBearerToken());
    }

    @Test
    void testClientBuilderWithBearerToken() {
        InvoiceIQClient client = InvoiceIQClient.builder()
                .bearerToken("test-bearer-token")
                .build();

        assertNotNull(client);
        InvoiceIQConfig config = client.getConfig();
        assertTrue(config.hasBearerToken());
        assertEquals("test-bearer-token", config.getBearerToken());
        assertFalse(config.hasApiKey());
    }

    @Test
    void testClientBuilderWithCustomBaseUrl() {
        InvoiceIQClient client = InvoiceIQClient.builder()
                .apiKey("test-api-key")
                .baseUrl("http://localhost:8080")
                .build();

        assertNotNull(client);
        assertEquals("http://localhost:8080", client.getConfig().getBaseUrl());
    }

    @Test
    void testClientBuilderWithCustomTimeouts() {
        InvoiceIQClient client = InvoiceIQClient.builder()
                .apiKey("test-api-key")
                .connectTimeout(10)
                .readTimeout(30)
                .writeTimeout(30)
                .build();

        assertNotNull(client);
        InvoiceIQConfig config = client.getConfig();
        assertEquals(10, config.getConnectTimeout());
        assertEquals(30, config.getReadTimeout());
        assertEquals(30, config.getWriteTimeout());
    }

    @Test
    void testClientBuilderThrowsExceptionWithoutAuth() {
        assertThrows(IllegalArgumentException.class, () -> {
            InvoiceIQClient.builder().build();
        });
    }
}
