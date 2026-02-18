package se.kth.iv1201.recruitment.service.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.repository.PersonRepository;

/**
 * Implementation av {@link UserDetailsService}.
 *
 * Används av Spring Security vid inloggning för att:
 * - hämta användare från databasen,
 * - hämta hashat lösenord,
 * - konvertera roll från databasen till en GrantedAuthority.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    /**
     * Skapar en ny instans av {@code UserDetailsServiceImpl}.
     *
     * @param personRepository repository för att hämta användare från databasen
     */
    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Hämtar användare från databasen baserat på användarnamn och bygger ett {@link UserDetails}.
     *
     * Rollen i databasen konverteras till Spring Security-format:
     * exempel, "recruiter" -> "ROLE_RECRUITER".
     *
     * @param username användarnamnet som ska laddas
     * @return {@link UserDetails} för Spring Security
     * @throws UsernameNotFoundException om användaren inte finns, eller saknar roll
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        if (person.getRole() == null) {
            throw new UsernameNotFoundException("User has no role assigned");
        }

        String roleName = "ROLE_" + person.getRole().getName().toUpperCase();

        return User.builder()
                .username(person.getUsername())
                .password(person.getPassword())
                .authorities(new SimpleGrantedAuthority(roleName))
                .build();
    }
}
