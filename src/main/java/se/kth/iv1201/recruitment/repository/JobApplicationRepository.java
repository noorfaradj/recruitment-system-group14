package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.recruitment.domain.JobApplication;

/**
 * Hanterar databasanrop för jobbansökningar.
 */
@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    // Spring Data JPA genererar automatiskt implementationen för grundläggande CRUD.
}