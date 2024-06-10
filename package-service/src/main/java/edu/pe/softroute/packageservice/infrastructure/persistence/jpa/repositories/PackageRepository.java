package edu.pe.softroute.packageservice.infrastructure.persistence.jpa.repositories;

import edu.pe.softroute.packageservice.domain.models.entities.Package;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, UUID> {

  List<Package> findAllByCompanyId(UUID companyId);
}
