package edu.pe.softroute.iamservice.infrastructure.persistence.jpa.repositories;

import edu.pe.softroute.iamservice.domain.models.entities.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByEmail(String email);

  List<User> findAllByIdIn(List<UUID> ids);

  boolean existsByEmail(String email);
}
