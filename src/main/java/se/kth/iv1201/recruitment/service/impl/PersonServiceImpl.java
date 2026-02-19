package se.kth.iv1201.recruitment.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.domain.Role;
import se.kth.iv1201.recruitment.dto.UserRegistrationDTO;
import se.kth.iv1201.recruitment.repository.PersonRepository;
import se.kth.iv1201.recruitment.repository.RoleRepository;
import se.kth.iv1201.recruitment.service.PersonService;

/**
 * Implementation av {@link PersonService} som hanterar användarregistrering.
 * <p>
 * Klassen ansvarar för att mappa data, hasha lösenord samt kontrollera 
 * att användarnamn och e-post är unika innan lagring sker.
 * </p>
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Skapar en ny instans av PersonServiceImpl.
     *
     * @param personRepository repository för persondata.
     * @param roleRepository repository för roller.
     * @param passwordEncoder verktyg för säker hashing av lösenord.
     */
    public PersonServiceImpl(PersonRepository personRepository,
                             RoleRepository roleRepository,
                             PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registrerar en ny användare i systemet.
     * <p>
     * Kontrollerar först om användarnamn eller e-post redan är registrerat 
     * för att kunna ge specifik feedback till användaren.
     * </p>
     *
     * @param dto dataöverföringsobjekt innehållande registreringsuppgifter.
     * @throws RuntimeException om användarnamn/e-post redan existerar eller om roll saknas.
     */
    @Override
    public void registerUser(UserRegistrationDTO dto) {
        
        if (personRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username '" + dto.getUsername() + "' is already taken.");
        }

        if (personRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email '" + dto.getEmail() + "' is already registered.");
        }

        Person person = new Person();
        person.setUsername(dto.getUsername());
        person.setName(dto.getName());
        person.setSurname(dto.getSurname());
        person.setPnr(dto.getPnr());
        person.setEmail(dto.getEmail());

        // Task 7: Obligatorisk BCrypt-hashing av lösenord innan lagring i databasen.
        person.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role applicantRole = roleRepository.findByName("applicant");
        if (applicantRole == null) {
            throw new RuntimeException("Error: System role 'applicant' missing.");
        }
        person.setRole(applicantRole);

        personRepository.save(person);
    }
}