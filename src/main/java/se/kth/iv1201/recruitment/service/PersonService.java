package se.kth.iv1201.recruitment.service;

import se.kth.iv1201.recruitment.dto.UserRegistrationDTO;

/**
 * Tjänstegränssnitt för hantering av användare.
 * Definierar affärslogik för registrering av nya användare.
 */
public interface PersonService {

    /**
     * Registrerar en ny användare i systemet.
     *
     * Implementationen ansvarar för att:
     * - mappa DTO till Person-entity,
     * - hasha lösenord,
     * - tilldela korrekt roll,
     * - spara användaren i databasen.
     *
     * @param dto registreringsdata från formuläret
     */
    void registerUser(UserRegistrationDTO dto);
}
