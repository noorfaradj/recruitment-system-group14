package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.recruitment.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // Om denna rad saknas kommer "personRepository.findByUsername" att lysa rött!
    Person findByUsername(String username); 
}