package se.kth.iv1201.recruitment.dto;

import se.kth.iv1201.recruitment.domain.ApplicationStatus;

/**
 * Data Transfer Object (DTO) som används för att presentera en jobbansökan i listvyn.
 * Innehåller endast den information som behövs för att visa listan för rekryteraren.
 */
public class ApplicationListItemDTO {

    private final Long id;
    private final String fullName;
    private final ApplicationStatus status;

    /**
     * Skapar en ny instans av ApplicationListItemDTO.
     *
     * @param id       Ansökans unika ID.
     * @param fullName Den sökandes fullständiga namn.
     * @param status   Ansökans nuvarande status.
     */
    public ApplicationListItemDTO(Long id, String fullName, ApplicationStatus status) {
        this.id = id;
        this.fullName = fullName;
        this.status = status;
    }

    /**
     * Hämtar ansökans ID.
     *
     * @return ID för ansökan.
     */
    public Long getId() {
        return id;
    }

    /**
     * Hämtar den sökandes fullständiga namn.
     *
     * @return Namnet på den sökande.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Hämtar status för ansökan.
     *
     * @return Status (t.ex. ACCEPTED, REJECTED, UNHANDLED).
     */
    public ApplicationStatus getStatus() {
        return status;
    }
}