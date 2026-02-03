package se.kth.iv1201.recruitment.service.impl;

import org.springframework.stereotype.Service;
import se.kth.iv1201.recruitment.domain.JobApplication;
import se.kth.iv1201.recruitment.dto.ApplicationListItemDTO;
import se.kth.iv1201.recruitment.repository.JobApplicationRepository;
import se.kth.iv1201.recruitment.service.JobApplicationService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the JobApplicationService interface.
 */
@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository repository;

    /**
     * Creates a JobApplicationServiceImpl.
     *
     * @param repository the job application repository
     */
    public JobApplicationServiceImpl(JobApplicationRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ApplicationListItemDTO> listAllApplications() {
        return repository.findAll()
                .stream()
                .map(app -> new ApplicationListItemDTO(
                        app.getId(),
                        app.getFirstName() + " " + app.getLastName(),
                        app.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
