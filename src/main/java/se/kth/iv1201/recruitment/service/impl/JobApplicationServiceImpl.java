package se.kth.iv1201.recruitment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.*;
import se.kth.iv1201.recruitment.dto.*;
import se.kth.iv1201.recruitment.repository.*;
import se.kth.iv1201.recruitment.service.JobApplicationService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation av tjänstegränssnittet för jobbansökningar.
 * Hanterar affärslogik och transaktioner för ansökningshantering,
 * inklusive listning och registrering av kompetenser och tillgänglighet.
 */
@Service
@Transactional
public class JobApplicationServiceImpl implements JobApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(JobApplicationServiceImpl.class);
    
    private final JobApplicationRepository repository;
    private final PersonRepository personRepository;
    private final CompetenceRepository competenceRepository;
    private final CompetenceProfileRepository competenceProfileRepository;
    private final AvailabilityRepository availabilityRepository;

    /**
     * Skapar en ny instans av JobApplicationServiceImpl med nödvändiga repositories.
     *
     * @param repository Repository för jobbansökningar.
     * @param personRepository Repository för personuppgifter.
     * @param competenceRepository Repository för kompetenstyper.
     * @param competenceProfileRepository Repository för koppling mellan person och kompetens.
     * @param availabilityRepository Repository för tillgänglighetsperioder.
     */
    public JobApplicationServiceImpl(JobApplicationRepository repository, 
                                     PersonRepository personRepository,
                                     CompetenceRepository competenceRepository,
                                     CompetenceProfileRepository competenceProfileRepository,
                                     AvailabilityRepository availabilityRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
        this.competenceRepository = competenceRepository;
        this.competenceProfileRepository = competenceProfileRepository;
        this.availabilityRepository = availabilityRepository;
    }

    /**
     * Hämtar en lista över alla jobbansökningar transformerade till DTO-objekt.
     * Använder optimerad hämtning för att undvika N+1-problem.
     *
     * @return En lista med {@link ApplicationListItemDTO}.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ApplicationListItemDTO> listAllApplications() {
        logger.info("Hämtar ansökningar med JOIN FETCH för optimerad prestanda.");
        return repository.findAllWithPerson().stream()
                .map(app -> new ApplicationListItemDTO(
                        app.getId(),
                        app.getPerson().getName() + " " + app.getPerson().getSurname(),
                        app.getPerson().getEmail(),
                        app.getStatus()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Sparar en fullständig jobbansökan inkluderat kompetensprofiler och tillgänglighetsperioder.
     * Metoden är transaktionell (ACID) för att säkerställa att hela ansökan sparas korrekt
     * eller inte alls vid eventuella fel (Task 10).
     *
     * @param form DTO innehållande kompetenser och tillgänglighet från formuläret.
     * @param username Användarnamnet för den sökande personen.
     * @throws RuntimeException Om användaren eller kompetensen inte kan hittas i databasen.
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFullApplication(ApplicationFormDTO form, String username) {
        logger.info("Initierar lagring av fullständig ansökan för användare: {}", username);
        
        // Åtgärdar Type Mismatch: Packar upp Optional<Person>
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("Misslyckades att hitta användare: {}", username);
                    return new RuntimeException("User not found: " + username);
                });

        // 1. Spara Kompetensprofiler (Mappar person till kompetens)
        if (form.getCompetences() != null) {
            for (CompetenceRowDTO cDto : form.getCompetences()) {
                if (cDto.getCompetenceId() != null && cDto.getYears() != null) {
                    CompetenceProfile profile = new CompetenceProfile();
                    profile.setPerson(person);
                    profile.setCompetence(competenceRepository.findById(cDto.getCompetenceId())
                            .orElseThrow(() -> new RuntimeException("Competence type not found")));
                    profile.setYears(cDto.getYears());
                    competenceProfileRepository.save(profile);
                }
            }
        }

        // 2. Spara Tillgänglighetsperioder
        if (form.getAvailabilities() != null) {
            for (AvailabilityRowDTO aDto : form.getAvailabilities()) {
                if (aDto.getFromDate() != null && aDto.getToDate() != null) {
                    Availability avail = new Availability();
                    avail.setPerson(person);
                    avail.setFromDate(aDto.getFromDate());
                    avail.setToDate(aDto.getToDate());
                    availabilityRepository.save(avail);
                }
            }
        }

        // 3. Skapa själva huvudposten för ansökan
        JobApplication app = new JobApplication();
        app.setPerson(person);
        app.setStatus("UNHANDLED");
        repository.save(app);
        
        logger.info("Ansökan sparad framgångsrikt för användare: {}", username);
    }
}