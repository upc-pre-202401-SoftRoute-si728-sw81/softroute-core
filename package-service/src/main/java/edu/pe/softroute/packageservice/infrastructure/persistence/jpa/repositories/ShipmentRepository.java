package edu.pe.softroute.packageservice.infrastructure.persistence.jpa.repositories;

import edu.pe.softroute.packageservice.domain.models.entities.Shipment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {

  Shipment findByCode(String code);
}
