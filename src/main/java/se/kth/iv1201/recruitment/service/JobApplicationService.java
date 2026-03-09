package se.kth.iv1201.recruitment.service;

import java.util.List;

import se.kth.iv1201.recruitment.dto.ApplicationFormDTO;
import se.kth.iv1201.recruitment.dto.ApplicationListItemDTO;

/**
 * Tjänstegränssnitt för hantering av jobbansökningar.
 *
 * Definierar affärsoperationer relaterade till:
 * - listning av ansökningar för rekryterare,
 * - registrering av nya ansökningar från sökande.
 */
public interface JobApplicationService {

    /**
     * Hämtar samtliga jobbansökningar och mappar dem till
     * {@link ApplicationListItemDTO}.
     *
     * @return lista av ansökningar i DTO-format
     */
    List<ApplicationListItemDTO> listAllApplications();

    /**
     * Sparar en fullständig jobbansökan.
     *
     * Implementationen ansvarar för att lagra:
     * - kompetensprofiler,
     * - tillgänglighetsperioder,
     * - huvudposten för ansökan.
     *
     * @param form data från ansökningsformuläret
     * @param username användarnamn för den sökande
     */
    void saveFullApplication(ApplicationFormDTO form, String username);
}
