package se.kth.iv1201.recruitment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public JobApplicationServiceImpl(JobApplicationRepository repository) {
        this.repository = repository;
    }

    /**
     * Hämtar alla jobbansökningar och mappar dem till DTOs.
     * Nu hämtas namn via Person-relationen för att undvika loopar och matchar nya JobApplication.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ApplicationListItemDTO> listAllApplications() {
        logger.info("Hämtar ansökningar med JOIN FETCH för snabbare laddning...");
        
        // Byt ut findAll() mot vår nya findAllWithPerson()
        return repository.findAllWithPerson().stream()
                .map(app -> new ApplicationListItemDTO(
                        app.getId(),
                        app.getPerson().getName() + " " + app.getPerson().getSurname(),
                        app.getPerson().getEmail(),
                        app.getStatus()
                ))
                .collect(Collectors.toList());
    }
}