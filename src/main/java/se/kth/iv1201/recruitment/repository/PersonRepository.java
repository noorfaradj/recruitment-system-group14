package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.iv1201.recruitment.domain.Person;
import java.util.Optional;

/**
 * Repository för hantering av personuppgifter i databasen.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    /**
     * Hittar en person baserat på användarnamn.
     * @param username Användarnamnet att söka efter.
     * @return En Optional innehållande personen om den hittas.
     */
    Optional<Person> findByUsername(String username);
}