package se.kth.iv1201.recruitment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import se.kth.iv1201.recruitment.domain.Availability;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {}