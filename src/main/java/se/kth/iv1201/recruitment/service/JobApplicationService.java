package se.kth.iv1201.recruitment.service;

import se.kth.iv1201.recruitment.dto.ApplicationListItemDTO;
import java.util.List;

/**
 * Service interface for job application-related operations.
 */
public interface JobApplicationService {

    /**
     * Retrieves a list of all job applications.
     *
     * @return A list of ApplicationListItemDTO objects representing job applications.
     */
    List<ApplicationListItemDTO> listAllApplications();
}