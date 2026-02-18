package se.kth.iv1201.recruitment.service;

import se.kth.iv1201.recruitment.dto.ApplicationListItemDTO;
import se.kth.iv1201.recruitment.dto.ApplicationFormDTO;
import java.util.List;

/**
 * Tjänstegränssnitt för hantering av jobbansökningar.
 * Definierar affärslogik för att lista existerande ansökningar samt för att
 * registrera nya ansökningar med kompetensprofiler och tillgänglighetsperioder.
 */
public interface JobApplicationService {

    /**
     * Hämtar en lista över samtliga jobbansökningar i systemet transformerade till DTO-objekt.
     * Denna metod används primärt av rekryterare för att få en överblick över inkomna ansökningar.
     *
     * @return En lista innehållande {@link ApplicationListItemDTO} för alla ansökningar.
     */
    List<ApplicationListItemDTO> listAllApplications();

    /**
     * Sparar en fullständig jobbansökan i databasen.
     * Metoden hanterar lagring av både kompetensprofiler och tillgänglighetsperioder
     * knutna till en specifik användare i en sammanhängande transaktion.
     *
     * @param form DTO-objekt som innehåller data från ansökningsformuläret.
     * @param username Användarnamnet för den sökande som skickar in ansökan.
     */
    void saveFullApplication(ApplicationFormDTO form, String username);
}