package edu.pe.softroute.organizationservice.infrastructure.persistence.jpa.repositories;

import edu.pe.softroute.organizationservice.domain.models.entities.Company;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
