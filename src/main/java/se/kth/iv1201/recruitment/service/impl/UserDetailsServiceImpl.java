package se.kth.iv1201.recruitment.service.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.repository.PersonRepository;

import java.util.Collections;

/**
 * Tjänst som integrerar applikationens användardatabas med Spring Security.
 * Används vid inloggning för att verifiera användarens referenser.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Hämtar en användare från databasen baserat på användarnamn och skapar ett
     * Spring Security User-objekt.
     *
     * @param username Användarnamnet som matades in vid inloggning.
     * @return UserDetails Ett objekt som Spring Security använder för autentisering.
     * @throws UsernameNotFoundException Om användaren inte hittas i databasen.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }
        
        // Returnerar Spring Securitys inbyggda User-objekt med lösenordet (som måste vara hashat i DB)
        return new User(
                person.getUsername(),
                person.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + person.getRole().getName().toUpperCase()))
        );
    }
}