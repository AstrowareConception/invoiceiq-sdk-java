package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Rapport de transformation contenant le score de conformité.
 */
public class TransformationReport {
    @SerializedName("transformation")
    private String transformation;

    @SerializedName("finalScore")
    private Integer finalScore;

    @SerializedName("profile")
    private String profile;

    @SerializedName("issues")
    private List<ReportIssue> issues;

    public TransformationReport() {
    }

    public String getTransformation() {
        return transformation;
    }

    public void setTransformation(String transformation) {
        this.transformation = transformation;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<ReportIssue> getIssues() {
        return issues;
    }

    public void setIssues(List<ReportIssue> issues) {
        this.issues = issues;
    }

    public boolean isSuccess() {
        return "success".equalsIgnoreCase(transformation);
    }

    public boolean hasIssues() {
        return issues != null && !issues.isEmpty();
    }

    public static class ReportIssue {
        @SerializedName("message")
        private String message;

        @SerializedName("code")
        private String code;

        public ReportIssue() {
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
