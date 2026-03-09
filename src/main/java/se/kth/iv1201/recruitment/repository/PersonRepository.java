package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import se.kth.iv1201.recruitment.domain.Person;

/**
 * Repository för hantering av {@link Person}-entiteter.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    /**
     * Söker efter en person baserat på användarnamn.
     *
     * @param username Användarnamnet att söka efter.
     * @return En Optional som innehåller personen om den hittas.
     */
    Optional<Person> findByUsername(String username);

    /**
     * Söker efter en person baserat på e-postadress.
     *
     * @param email E-postadressen att söka efter.
     * @return En Optional som innehåller personen om den hittas.
     */
    Optional<Person> findByEmail(String email);
}