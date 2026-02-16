package se.kth.iv1201.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kth.iv1201.recruitment.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Om denna rad saknas kommer "roleRepository.findByName" att lysa rött!
    Role findByName(String name);
}