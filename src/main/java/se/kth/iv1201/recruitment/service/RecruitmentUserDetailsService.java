package se.kth.iv1201.recruitment.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.repository.PersonRepository;

@Service
public class RecruitmentUserDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    public RecruitmentUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String roleName = person.getRole().getName(); // recruiter / applicant
        String springRole = "ROLE_" + roleName.toUpperCase(); // ROLE_RECRUITER

        return new User(
                person.getUsername(),
                person.getPassword(),
                List.of(new SimpleGrantedAuthority(springRole))
        );
    }
}
