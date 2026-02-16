package se.kth.iv1201.recruitment.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.*;
import se.kth.iv1201.recruitment.dto.UserRegistrationDTO;
import se.kth.iv1201.recruitment.repository.*;
import se.kth.iv1201.recruitment.service.PersonService;

@Service
@Transactional // Task 10: Hanterar transaktioner 
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonServiceImpl(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
public void registerUser(UserRegistrationDTO dto) {
    Person person = new Person();
    person.setUsername(dto.getUsername());
    person.setName(dto.getName());
    person.setSurname(dto.getSurname());
    person.setPnr(dto.getPnr());
    person.setEmail(dto.getEmail());
    
    person.setPassword(passwordEncoder.encode(dto.getPassword()));

    Role applicantRole = roleRepository.findByName("applicant");
    
    // Bra att lägga till för att felsöka snabbare
    if (applicantRole == null) {
        throw new RuntimeException("Error: Role 'applicant' not found in database. Check your seed data!");
    }
    
    person.setRole(applicantRole);
    personRepository.save(person);
}
}