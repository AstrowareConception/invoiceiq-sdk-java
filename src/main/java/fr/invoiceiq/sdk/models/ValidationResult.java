package fr.invoiceiq.sdk.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Résultat d'une validation de document Factur-X.
 */
public class ValidationResult {
    @SerializedName("id")
    private String id;

    @SerializedName("status")
    private String status;

    @SerializedName("isValid")
    private Boolean isValid;

    @SerializedName("score")
    private Integer score;

    @SerializedName("profile")
    private String profile;

    @SerializedName("issues")
    private List<ValidationIssue> issues;

    @SerializedName("createdAt")
    private String createdAt;

    public ValidationResult() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<ValidationIssue> getIssues() {
        return issues;
    }

    public void setIssues(List<ValidationIssue> issues) {
        this.issues = issues;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isValid() {
        return Boolean.TRUE.equals(isValid);
    }

    public boolean hasIssues() {
        return issues != null && !issues.isEmpty();
    }

    public static class ValidationIssue {
        @SerializedName("message")
        private String message;

        @SerializedName("code")
        private String code;

        @SerializedName("severity")
        private String severity;

        @SerializedName("path")
        private String path;

        public ValidationIssue() {
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

        public String getSeverity() {
            return severity;
        }

        public void setSeverity(String severity) {
            this.severity = severity;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
