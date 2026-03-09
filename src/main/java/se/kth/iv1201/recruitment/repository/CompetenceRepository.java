package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitment.domain.Competence;

/**
 * Repository för Competence-entityn.
 * Tillhandahåller standard CRUD-operationer via Spring Data JPA.
 */
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
}
