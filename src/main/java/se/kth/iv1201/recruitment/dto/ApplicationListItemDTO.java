package se.kth.iv1201.recruitment.dto;

import se.kth.iv1201.recruitment.domain.ApplicationStatus;

/**
 * DTO used for transferring job application data to the recruiter UI.
 */
public class ApplicationListItemDTO {

    private final Long id;
    private final String fullName;
    private final ApplicationStatus status;

    /**
     * Creates an instance of ApplicationListItemDTO.
     *
     * @param id       The ID of the job application.
     * @param fullName The full name of the applicant.
     * @param status   The status of the job application.
     */
    public ApplicationListItemDTO(Long id, String fullName, ApplicationStatus status) {
        this.id = id;
        this.fullName = fullName;
        this.status = status;
    }

    /**
     * Gets the ID of the job application.
     *
     * @return The ID of the job application.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the full name of the applicant.
     *
     * @return The full name of the applicant.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Gets the status of the job application.
     *
     * @return The status of the job application.
     */
    public ApplicationStatus getStatus() {
        return status;
    }
}