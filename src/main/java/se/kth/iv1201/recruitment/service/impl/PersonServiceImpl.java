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
 * Implementation av PersonService.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonServiceImpl(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Skapar och sparar en ny användare i databasen. 
     * Implementerar Task 6 genom att kryptera lösenordet innan sparning.
     *
     * @param dto Data från formuläret.
     */
    @Override
    public void registerUser(UserRegistrationDTO dto) {
        Person person = new Person();
        person.setUsername(dto.getUsername());
        person.setName(dto.getName());
        person.setSurname(dto.getSurname());
        person.setPnr(dto.getPnr());
        person.setEmail(dto.getEmail());

        // Task 6: Hasha lösenordet med BCrypt
        person.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Standardroll för nya konton är 'applicant'
        Role applicantRole = roleRepository.findByName("applicant");
        person.setRole(applicantRole);

        personRepository.save(person);
    }
}