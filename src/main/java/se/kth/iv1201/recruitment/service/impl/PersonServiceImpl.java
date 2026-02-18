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
 * Implementation av {@link PersonService}.
 *
 * Ansvar:
 * - registrera nya användare,
 * - hasha lösenord med BCrypt,
 * - koppla användaren till rätt roll.
 *
 * Klassen är transaktionell för att säkerställa
 * att registreringen sparas atomiskt (Task 10).
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Skapar en ny instans av {@code PersonServiceImpl}.
     *
     * @param personRepository repository för persondata
     * @param roleRepository repository för roller
     * @param passwordEncoder encoder för lösenord (BCrypt)
     */
    public PersonServiceImpl(PersonRepository personRepository,
                             RoleRepository roleRepository,
                             PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registrerar en ny användare.
     *
     * Metoden:
     * - mappar DTO till Person-entity,
     * - hashar lösenordet,
     * - kopplar rollen "applicant",
     * - sparar användaren i databasen.
     *
     * @param dto registreringsdata från formuläret
     * @throws RuntimeException om rollen inte finns i databasen
     */
    @Override
    public void registerUser(UserRegistrationDTO dto) {

        Person person = new Person();
        person.setUsername(dto.getUsername());
        person.setName(dto.getName());
        person.setSurname(dto.getSurname());
        person.setPnr(dto.getPnr());
        person.setEmail(dto.getEmail());

        // Task 7: BCrypt-hashing av lösenord
        person.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role applicantRole = roleRepository.findByName("applicant");

        if (applicantRole == null) {
            throw new RuntimeException(
                    "Error: Role 'applicant' not found in database. Check your seed data!");
        }

        person.setRole(applicantRole);
        personRepository.save(person);
    }
}
