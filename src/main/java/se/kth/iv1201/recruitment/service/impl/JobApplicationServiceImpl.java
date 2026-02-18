package se.kth.iv1201.recruitment.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.recruitment.domain.Availability;
import se.kth.iv1201.recruitment.domain.CompetenceProfile;
import se.kth.iv1201.recruitment.domain.JobApplication;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.dto.ApplicationFormDTO;
import se.kth.iv1201.recruitment.dto.ApplicationListItemDTO;
import se.kth.iv1201.recruitment.dto.AvailabilityRowDTO;
import se.kth.iv1201.recruitment.dto.CompetenceRowDTO;
import se.kth.iv1201.recruitment.repository.AvailabilityRepository;
import se.kth.iv1201.recruitment.repository.CompetenceProfileRepository;
import se.kth.iv1201.recruitment.repository.CompetenceRepository;
import se.kth.iv1201.recruitment.repository.JobApplicationRepository;
import se.kth.iv1201.recruitment.repository.PersonRepository;
import se.kth.iv1201.recruitment.service.JobApplicationService;

/**
 * Service-implementation av {@link JobApplicationService}.
 *
 * Ansvar:
 * - läsa jobbansökningar för rekryterarvyn,
 * - spara en full ansökan (kompetenser, tillgänglighet, huvudansökan).
 *
 * Klassen är transaktionell för att säkerställa att flera relaterade skrivningar
 * i databasen sker atomiskt (Task 10).
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
     * Skapar en ny instans av {@code JobApplicationServiceImpl}.
     *
     * @param repository repository för jobbansökningar
     * @param personRepository repository för personuppgifter
     * @param competenceRepository repository för kompetenser
     * @param competenceProfileRepository repository för kompetensprofiler
     * @param availabilityRepository repository för tillgänglighetsperioder
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
     * Hämtar alla jobbansökningar och mappar dem till {@link ApplicationListItemDTO}.
     *
     * Metoden använder en optimerad databasfråga (JOIN FETCH) för att undvika N+1-problem
     * och för att persondata ska finnas tillgänglig när DTO:er skapas.
     *
     * @return lista av ansökningar i DTO-format
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
     * Sparar en fullständig ansökan för en användare.
     *
     * Process:
     * 1) spara kompetensprofiler,
     * 2) spara tillgänglighetsperioder,
     * 3) skapa huvudposten {@link JobApplication}.
     *
     * Metoden körs i en och samma transaktion för att garantera att hela ansökan sparas
     * atomiskt, vid fel rullas allt tillbaka (Task 10).
     *
     * @param form DTO som innehåller kompetenser och tillgänglighet från formuläret
     * @param username användarnamnet för den sökande personen
     * @throws RuntimeException om användaren inte finns, eller om en kompetens inte kan hittas
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFullApplication(ApplicationFormDTO form, String username) {
        logger.info("Initierar lagring av fullständig ansökan för användare: {}", username);

        // Packar upp Optional<Person>
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("Misslyckades att hitta användare: {}", username);
                    return new RuntimeException("User not found: " + username);
                });

        // 1. Spara kompetensprofiler (mappar person till kompetens)
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

        // 2. Spara tillgänglighetsperioder
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
