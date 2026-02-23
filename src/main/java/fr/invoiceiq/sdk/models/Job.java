package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Représente un job (transformation ou génération) dans le système.
 */
public class Job {
    @SerializedName("id")
    private String id;

    @SerializedName("status")
    private JobStatus status;

    @SerializedName("downloadUrl")
    private String downloadUrl;

    @SerializedName("reportDownloadUrl")
    private String reportDownloadUrl;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("completedAt")
    private String completedAt;

    @SerializedName("error")
    private String error;

    public Job() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getReportDownloadUrl() {
        return reportDownloadUrl;
    }

    public void setReportDownloadUrl(String reportDownloadUrl) {
        this.reportDownloadUrl = reportDownloadUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isCompleted() {
        return status == JobStatus.COMPLETED;
    }

    public boolean isFailed() {
        return status == JobStatus.FAILED;
    }

    public boolean isPending() {
        return status == JobStatus.PENDING || status == JobStatus.PROCESSING;
    }

    public enum JobStatus {
        @SerializedName("pending")
        PENDING,

        @SerializedName("processing")
        PROCESSING,

        @SerializedName("completed")
        COMPLETED,

        @SerializedName("failed")
        FAILED
    }
}
