package se.kth.iv1201.recruitment.service;

import se.kth.iv1201.recruitment.dto.ApplicationListItemDTO;
import java.util.List;

/**
 * Gränssnitt för tjänsten som hanterar jobbansökningar.
 */
public interface JobApplicationService {
    /**
     * Hämtar en lista över alla ansökningar i form av DTO-objekt.
     * @return En lista med ApplicationListItemDTO.
     */
    List<ApplicationListItemDTO> listAllApplications();
}