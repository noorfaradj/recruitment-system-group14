package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.recruitment.domain.JobApplication;
import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    
    // Denna JOIN FETCH tvingar databasen att hämta allt i EN ENDA fråga.
    // Det är skillnaden mellan 1 sekund och 30 sekunder.
    @Query("SELECT ja FROM JobApplication ja JOIN FETCH ja.person")
    List<JobApplication> findAllWithPerson();
}