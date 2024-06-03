package edu.pe.softroute.iamservice.infrastructure.persistence.jpa.repositories;

import edu.pe.softroute.iamservice.domain.models.entities.Role;
import edu.pe.softroute.iamservice.domain.models.enums.Roles;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(Roles name);
}
