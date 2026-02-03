package se.kth.iv1201.recruitment.domain;

/**
 * Enum representing the status of a job application.
 */
public enum ApplicationStatus {
    /**
     * The application has been accepted.
     */
    ACCEPTED,

    /**
     * The application has been rejected.
     */
    REJECTED,

    /**
     * The application is still under review.
     */
    UNHANDLED
}