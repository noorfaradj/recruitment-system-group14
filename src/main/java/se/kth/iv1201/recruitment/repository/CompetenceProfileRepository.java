package se.kth.iv1201.recruitment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.iv1201.recruitment.domain.CompetenceProfile;

public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile, Long> {}