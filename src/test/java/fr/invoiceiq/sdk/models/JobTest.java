package fr.invoiceiq.sdk.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests pour Job.
 */
class JobTest {

    @Test
    void testJobStatusChecks() {
        Job job = new Job();

        job.setStatus(Job.JobStatus.PENDING);
        assertTrue(job.isPending());
        assertFalse(job.isCompleted());
        assertFalse(job.isFailed());

        job.setStatus(Job.JobStatus.PROCESSING);
        assertTrue(job.isPending());
        assertFalse(job.isCompleted());
        assertFalse(job.isFailed());

        job.setStatus(Job.JobStatus.COMPLETED);
        assertFalse(job.isPending());
        assertTrue(job.isCompleted());
        assertFalse(job.isFailed());

        job.setStatus(Job.JobStatus.FAILED);
        assertFalse(job.isPending());
        assertFalse(job.isCompleted());
        assertTrue(job.isFailed());
    }

    @Test
    void testJobProperties() {
        Job job = new Job();
        job.setId("job-123");
        job.setStatus(Job.JobStatus.COMPLETED);
        job.setDownloadUrl("https://api.invoiceiq.fr/downloads/abc");
        job.setReportDownloadUrl("https://api.invoiceiq.fr/reports/abc");
        job.setCreatedAt("2024-02-22T10:00:00Z");
        job.setCompletedAt("2024-02-22T10:05:00Z");

        assertEquals("job-123", job.getId());
        assertEquals(Job.JobStatus.COMPLETED, job.getStatus());
        assertEquals("https://api.invoiceiq.fr/downloads/abc", job.getDownloadUrl());
        assertEquals("https://api.invoiceiq.fr/reports/abc", job.getReportDownloadUrl());
        assertEquals("2024-02-22T10:00:00Z", job.getCreatedAt());
        assertEquals("2024-02-22T10:05:00Z", job.getCompletedAt());
    }
}
