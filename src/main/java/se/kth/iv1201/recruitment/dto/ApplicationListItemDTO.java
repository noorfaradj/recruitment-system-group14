package se.kth.iv1201.recruitment.dto;

/**
 * Data Transfer Object (DTO) som används för att presentera en jobbansökan i listvyn.
 * Innehåller den information som krävs för Use Case 5.4.
 */
public class ApplicationListItemDTO {

    private final Long id;
    private final String fullName;
    private final String email; // Lagt till email för att matcha HTML-vyn
    private final String status; // Ändrat från ApplicationStatus till String för stabilitet

    /**
     * Skapar en ny instans av ApplicationListItemDTO.
     */
    public ApplicationListItemDTO(Long id, String fullName, String email, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }
}