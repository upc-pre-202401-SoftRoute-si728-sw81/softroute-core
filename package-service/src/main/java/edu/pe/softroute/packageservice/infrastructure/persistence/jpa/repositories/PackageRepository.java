package edu.pe.softroute.packageservice.infrastructure.persistence.jpa.repositories;

import edu.pe.softroute.packageservice.domain.models.entities.Package;
import edu.pe.softroute.packageservice.domain.models.enums.PackageStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, UUID> {

  Package findByCode(String code);

  @Query("SELECT p FROM Package p ORDER BY p.createdAt DESC")
  List<Package> findAllByCompanyId(UUID companyId);

  @Query("SELECT p FROM Package p WHERE (p.status = :status) AND (p.companyId = :companyId) ORDER BY p.createdAt DESC")
  List<Package> findAllByCompanyIdAndShipmentStatus(UUID companyId, PackageStatus status);

  @Query("SELECT p FROM Package p WHERE (p.shipment.id = :shipmentId) ORDER BY p.createdAt DESC")
  List<Package> findAllByShipmentId(UUID shipmentId);
}
