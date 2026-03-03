package fr.invoiceiq.sdk.client;

import fr.invoiceiq.sdk.config.InvoiceIQConfig;
import fr.invoiceiq.sdk.exceptions.InvoiceIQException;
import fr.invoiceiq.sdk.models.Job;
import fr.invoiceiq.sdk.models.TransformationMetadata;
import fr.invoiceiq.sdk.models.TransformationReport;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Client pour les opérations de transformation PDF vers Factur-X.
 */
public class TransformationClient extends BaseHttpClient {

    public TransformationClient(InvoiceIQConfig config) {
        super(config);
    }

    /**
     * Transforme un PDF simple en Factur-X en incluant des métadonnées JSON.
     * Coût: 5 crédits.
     *
     * @param pdfFile        Le fichier PDF source
     * @param metadata       Les métadonnées de la facture
     * @param idempotencyKey Clé d'idempotence optionnelle
     * @return Le job de transformation
     * @throws InvoiceIQException En cas d'erreur
     */
    public Job transform(File pdfFile, TransformationMetadata metadata, String idempotencyKey) throws InvoiceIQException {
        String url = config.getBaseUrl() + "/api/v1/transformations";

        RequestBody fileBody = RequestBody.create(pdfFile, MediaType.parse("application/pdf"));
        String metadataJson = toJson(metadata);

        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", pdfFile.getName(), fileBody)
                .addFormDataPart("metadata", metadataJson);

        Request.Builder requestBuilder = buildRequest(url)
                .post(bodyBuilder.build());

        if (idempotencyKey != null && !idempotencyKey.isEmpty()) {
            requestBuilder.addHeader("Idempotency-Key", idempotencyKey);
        }

        Request request = requestBuilder.build();

        try (Response response = executeRequest(request)) {
            String responseBody = response.body().string();
            return fromJson(responseBody, Job.class);
        } catch (IOException e) {
            throw new InvoiceIQException("Failed to read transformation response", e);
        }
    }

    /**
     * Transforme un PDF simple en Factur-X.
     *
     * @param pdfFile  Le fichier PDF source
     * @param metadata Les métadonnées de la facture
     * @return Le job de transformation
     * @throws InvoiceIQException En cas d'erreur
     */
    public Job transform(File pdfFile, TransformationMetadata metadata) throws InvoiceIQException {
        return transform(pdfFile, metadata, null);
    }

    /**
     * Récupère le statut d'un job de transformation.
     *
     * @param jobId L'ID du job
     * @return Le job avec son statut actuel
     * @throws InvoiceIQException En cas d'erreur
     */
    public Job getJob(String jobId) throws InvoiceIQException {
        String url = config.getBaseUrl() + "/api/v1/transformations/" + jobId;

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
     * Télécharge le rapport de transformation.
     *
     * @param job Le job complété
     * @return Le rapport de transformation
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
