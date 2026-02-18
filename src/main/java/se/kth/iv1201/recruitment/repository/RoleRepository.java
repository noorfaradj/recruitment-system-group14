package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.kth.iv1201.recruitment.domain.Role;

/**
 * Repository för Role-entityn.
 * Används för att hämta roller från databasen.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Hittar en roll baserat på dess namn.
     *
     * @param name rollens namn
     * @return roll om den hittas
     */
    Role findByName(String name);
}
