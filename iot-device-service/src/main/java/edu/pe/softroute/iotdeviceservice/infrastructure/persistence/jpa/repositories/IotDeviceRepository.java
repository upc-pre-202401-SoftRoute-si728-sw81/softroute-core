package edu.pe.softroute.iotdeviceservice.infrastructure.persistence.jpa.repositories;

import edu.pe.softroute.iotdeviceservice.domain.models.entities.IotDevice;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IotDeviceRepository extends JpaRepository<IotDevice, UUID> {

  IotDevice findByTrackingNumber(String trackingNumber);
}
