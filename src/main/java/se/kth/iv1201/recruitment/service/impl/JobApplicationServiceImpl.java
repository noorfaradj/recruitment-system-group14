package se.kth.iv1201.recruitment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.kth.iv1201.recruitment.dto.ApplicationListItemDTO;
import se.kth.iv1201.recruitment.repository.JobApplicationRepository;
import se.kth.iv1201.recruitment.service.JobApplicationService;

import java.util.List;

/**
 * Default implementation of application business logic.
 */
@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private static final Logger log =
            LoggerFactory.getLogger(JobApplicationServiceImpl.class);

    private final JobApplicationRepository repository;

    public JobApplicationServiceImpl(JobApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ApplicationListItemDTO> listAllApplications() {
        log.info("Fetching all job applications");
        return repository.findAll().stream()
                .map(app -> new ApplicationListItemDTO(
                        app.getId(),
                        app.getFullName(),
                        app.getStatus()))
                .toList();
    }
}
