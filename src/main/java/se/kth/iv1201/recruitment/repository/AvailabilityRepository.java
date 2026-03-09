package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kth.iv1201.recruitment.domain.Availability;

/**
 * Repository för Availability-entityn.
 * Tillhandahåller standard CRUD-operationer
 * via Spring Data JPA.
 */
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
}
