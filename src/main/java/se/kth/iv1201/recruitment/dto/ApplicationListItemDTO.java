package se.kth.iv1201.recruitment.dto;

/**
 * Data Transfer Object (DTO) som används för att presentera
 * en jobbansökan i listvyn.
 * Innehåller den information som krävs för Use Case 5.4.
 */
public class ApplicationListItemDTO {

    private final Long id;
    private final String fullName;
    private final String email;
    private final String status;

    /**
     * Skapar en ny instans av ApplicationListItemDTO.
     *
     * @param id ansökans id
     * @param fullName sökandens fullständiga namn
     * @param email sökandens e-postadress
     * @param status ansökans status
     */
    public ApplicationListItemDTO(Long id, String fullName, String email, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.status = status;
    }

    /**
     * @return ansökans id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return sökandens fullständiga namn
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return sökandens e-postadress
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return ansökans status som text
     */
    public String getStatus() {
        return status;
    }
}
