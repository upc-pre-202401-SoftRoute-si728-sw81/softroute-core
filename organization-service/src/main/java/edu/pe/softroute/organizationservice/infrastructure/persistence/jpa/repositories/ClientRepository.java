package edu.pe.softroute.organizationservice.infrastructure.persistence.jpa.repositories;

import edu.pe.softroute.organizationservice.domain.models.entities.Client;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

  List<Client> findAllByCompanyId(UUID companyId);
}
