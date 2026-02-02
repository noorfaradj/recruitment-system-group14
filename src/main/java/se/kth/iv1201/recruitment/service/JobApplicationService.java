package se.kth.iv1201.recruitment.service;

import se.kth.iv1201.recruitment.dto.ApplicationListItemDTO;

import java.util.List;

/**
 * Business operations for job applications.
 */
public interface JobApplicationService {

    /**
     * Lists all job applications for recruiter view.
     *
     * @return applications prepared for presentation
     */
    List<ApplicationListItemDTO> listAllApplications();
}
