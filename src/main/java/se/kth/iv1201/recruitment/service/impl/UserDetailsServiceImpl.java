package se.kth.iv1201.recruitment.service.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.repository.PersonRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepository personRepository;

    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Hämtar person från DB
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Säkerhetskoll: Om roll saknas i DB, kasta fel istället för NullPointerException
        if (person.getRole() == null) {
            throw new UsernameNotFoundException("User has no role assigned");
        }

        // Konverterar små bokstäver (t.ex. recruiter) till ROLE_RECRUITER
        String roleName = "ROLE_" + person.getRole().getName().toUpperCase();

        return User.builder()
                .username(person.getUsername())
                .password(person.getPassword())
                .authorities(new SimpleGrantedAuthority(roleName))
                .build();
    }
}