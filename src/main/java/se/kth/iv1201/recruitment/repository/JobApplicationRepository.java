package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.kth.iv1201.recruitment.domain.JobApplication;

/**
 * Repository for accessing job applications in the database.
 */
@Repository
public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {
}