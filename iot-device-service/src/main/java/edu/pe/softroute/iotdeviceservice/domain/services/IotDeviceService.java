package edu.pe.softroute.iotdeviceservice.domain.services;


import edu.pe.softroute.iotdeviceservice.domain.models.entities.IotDevice;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

public interface IotDeviceService {

  IotDevice create();

  List<IotDevice> getAll();

  IotDevice getById(UUID id);

  ResponseEntity<?> delete(UUID id);
}
