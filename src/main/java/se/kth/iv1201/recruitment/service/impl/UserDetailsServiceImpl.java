package se.kth.iv1201.recruitment.service.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.repository.PersonRepository;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepository personRepository;

    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fixar Optional-felet genom orElseThrow
        Person person = personRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Task 6: Authorization [cite: 163]
        return new User(
            person.getUsername(),
            person.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + person.getRole().getName().toUpperCase()))
        );
    }
}