package se.kth.iv1201.recruitment.dto;

import se.kth.iv1201.recruitment.domain.ApplicationStatus;

/**
 * DTO used for listing applications in the recruiter UI.
 */
public class ApplicationListItemDTO {

    private final Long id;
    private final String fullName;
    private final ApplicationStatus status;

    public ApplicationListItemDTO(Long id, String fullName, ApplicationStatus status) {
        this.id = id;
        this.fullName = fullName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public ApplicationStatus getStatus() {
        return status;
    }
}
