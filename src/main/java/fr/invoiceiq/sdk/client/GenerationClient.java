package fr.invoiceiq.sdk.client;

import fr.invoiceiq.sdk.config.InvoiceIQConfig;
import fr.invoiceiq.sdk.exceptions.InvoiceIQException;
import fr.invoiceiq.sdk.models.GenerationPayload;
import fr.invoiceiq.sdk.models.Job;
import fr.invoiceiq.sdk.models.TransformationReport;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Client pour les opérations de génération complète de Factur-X.
 */
public class GenerationClient extends BaseHttpClient {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public GenerationClient(InvoiceIQConfig config) {
        super(config);
    }

    /**
     * Génère une facture Factur-X complète à partir d'un JSON.
     * Coût: 10 crédits.
     *
     * @param payload        Les données de génération
     * @param idempotencyKey Clé d'idempotence optionnelle
     * @return Le job de génération
     * @throws InvoiceIQException En cas d'erreur
     */
    public Job generate(GenerationPayload payload, String idempotencyKey) throws InvoiceIQException {
        String url = config.getBaseUrl() + "/api/v1/generations";

        String jsonBody = toJson(payload);
        RequestBody requestBody = RequestBody.create(jsonBody, JSON);

        Request.Builder requestBuilder = buildRequest(url)
                .post(requestBody);

        if (idempotencyKey != null && !idempotencyKey.isEmpty()) {
            requestBuilder.addHeader("Idempotency-Key", idempotencyKey);
        }

        Request request = requestBuilder.build();

        try (Response response = executeRequest(request)) {
            String responseBody = response.body().string();
            return fromJson(responseBody, Job.class);
        } catch (IOException e) {
            throw new InvoiceIQException("Failed to read generation response", e);
        }
    }

    /**
     * Génère une facture Factur-X complète à partir d'un JSON.
     *
     * @param payload Les données de génération
     * @return Le job de génération
     * @throws InvoiceIQException En cas d'erreur
     */
    public Job generate(GenerationPayload payload) throws InvoiceIQException {
        return generate(payload, null);
    }

    /**
     * Récupère le statut d'un job de génération.
     *
     * @param jobId L'ID du job
     * @return Le job avec son statut actuel
     * @throws InvoiceIQException En cas d'erreur
     */
    public Job getJob(String jobId) throws InvoiceIQException {
        String url = config.getBaseUrl() + "/api/v1/generations/" + jobId;

        Request request = buildRequest(url)
                .get()
                .build();

        try (Response response = executeRequest(request)) {
            String responseBody = response.body().string();
            return fromJson(responseBody, Job.class);
        } catch (IOException e) {
            throw new InvoiceIQException("Failed to read job response", e);
        }
    }

    /**
     * Télécharge le PDF Factur-X généré.
     *
     * @param job            Le job complété
     * @param destinationFile Le fichier de destination
     * @throws InvoiceIQException En cas d'erreur
     */
    public void downloadResult(Job job, File destinationFile) throws InvoiceIQException {
        if (!job.isCompleted()) {
            throw new InvoiceIQException("Job is not completed yet");
        }

        if (job.getDownloadUrl() == null || job.getDownloadUrl().isEmpty()) {
            throw new InvoiceIQException("Download URL is not available");
        }

        downloadFile(job.getDownloadUrl(), destinationFile);
    }

    /**
     * Télécharge le rapport de génération.
     *
     * @param job Le job complété
     * @return Le rapport de génération
     * @throws InvoiceIQException En cas d'erreur
     */
    public TransformationReport downloadReport(Job job) throws InvoiceIQException {
        if (!job.isCompleted()) {
            throw new InvoiceIQException("Job is not completed yet");
        }

        if (job.getReportDownloadUrl() == null || job.getReportDownloadUrl().isEmpty()) {
            throw new InvoiceIQException("Report download URL is not available");
        }

        Request request = buildRequest(job.getReportDownloadUrl())
                .get()
                .build();

        try (Response response = executeRequest(request)) {
            String responseBody = response.body().string();
            return fromJson(responseBody, TransformationReport.class);
        } catch (IOException e) {
            throw new InvoiceIQException("Failed to read report", e);
        }
    }

    /**
     * Attend la complétion d'un job avec polling.
     *
     * @param jobId           L'ID du job
     * @param pollIntervalMs  Intervalle de polling en ms
     * @param timeoutMs       Timeout en ms
     * @return Le job complété
     * @throws InvoiceIQException En cas d'erreur ou timeout
     */
    public Job waitForCompletion(String jobId, long pollIntervalMs, long timeoutMs) throws InvoiceIQException {
        long startTime = System.currentTimeMillis();

        while (true) {
            Job job = getJob(jobId);

            if (job.isCompleted() || job.isFailed()) {
                return job;
            }

            long elapsed = System.currentTimeMillis() - startTime;
            if (elapsed >= timeoutMs) {
                throw new InvoiceIQException("Timeout waiting for job completion");
            }

            try {
                Thread.sleep(pollIntervalMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new InvoiceIQException("Interrupted while waiting for job completion", e);
            }
        }
    }

    /**
     * Télécharge un fichier depuis une URL.
     */
    private void downloadFile(String url, File destinationFile) throws InvoiceIQException {
        Request request = buildRequest(url)
                .get()
                .build();

        try (Response response = executeRequest(request)) {
            try (InputStream inputStream = response.body().byteStream()) {
                Files.copy(inputStream, destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new InvoiceIQException("Failed to download file", e);
        }
    }
}
