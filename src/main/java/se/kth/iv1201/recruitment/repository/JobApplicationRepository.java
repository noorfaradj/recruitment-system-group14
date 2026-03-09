package se.kth.iv1201.recruitment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se.kth.iv1201.recruitment.domain.JobApplication;

/**
 * Repository för JobApplication-entityn.
 * Innehåller specialanpassade frågor för att optimera hämtning av data.
 */
@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    /**
     * Hämtar alla jobbansökningar tillsammans med kopplad person
     * i en enda databasfråga (JOIN FETCH) för att undvika
     * LazyInitializationException och N+1-problem.
     *
     * @return lista av jobbansökningar med person laddad
     */
    @Query("SELECT ja FROM JobApplication ja JOIN FETCH ja.person")
    List<JobApplication> findAllWithPerson();
}
