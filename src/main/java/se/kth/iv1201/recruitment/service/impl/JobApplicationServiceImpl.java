package se.kth.iv1201.recruitment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.ApplicationStatus;
import se.kth.iv1201.recruitment.dto.ApplicationListItemDTO;
import se.kth.iv1201.recruitment.repository.JobApplicationRepository;
import se.kth.iv1201.recruitment.service.JobApplicationService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation av tjänstegränssnittet för jobbansökningar.
 * Hanterar affärslogik och transaktioner för ansökningshantering.
 */
@Service
@Transactional
public class JobApplicationServiceImpl implements JobApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(JobApplicationServiceImpl.class);
    private final JobApplicationRepository repository;

    /**
     * Skapar en ny instans av JobApplicationServiceImpl.
     *
     * @param repository Repositoryt som används för databasåtkomst.
     */
    public JobApplicationServiceImpl(JobApplicationRepository repository) {
        this.repository = repository;
    }

    /**
     * Hämtar alla jobbansökningar från databasen och konverterar dem till DTO-objekt.
     *
     * @return En lista med ApplicationListItemDTO som representerar alla ansökningar.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ApplicationListItemDTO> listAllApplications() {
        logger.debug("Hämtar alla jobbansökningar från databasen.");
        
        return repository.findAll().stream()
                .map(app -> new ApplicationListItemDTO(
                        app.getId(),
                        app.getFirstName() + " " + app.getLastName(),
                        ApplicationStatus.valueOf(app.getStatus().toString())
                ))
                .collect(Collectors.toList());
    }
}