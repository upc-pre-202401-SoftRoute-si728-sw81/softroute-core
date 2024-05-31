package edu.pe.softroute.iotdeviceservice.application.internal.services;

import edu.pe.softroute.iotdeviceservice.domain.exceptions.IotDeviceNotFoundException;
import edu.pe.softroute.iotdeviceservice.domain.models.entities.IotDevice;
import edu.pe.softroute.iotdeviceservice.domain.models.enums.DeviceStatus;
import edu.pe.softroute.iotdeviceservice.domain.services.IotDeviceService;
import edu.pe.softroute.iotdeviceservice.infrastructure.persistence.jpa.repositories.IotDeviceRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IotDeviceServiceImpl implements IotDeviceService {

  private final IotDeviceRepository iotDeviceRepository;

  @Override
  public IotDevice create() {
    IotDevice iotDevice = new IotDevice();
    iotDevice.setStatus(DeviceStatus.OFFLINE);
    iotDevice.setTrackingNumber(null);

    return iotDeviceRepository.save(iotDevice);
  }

  @Override
  public List<IotDevice> getAll() {
    return iotDeviceRepository.findAll();
  }

  @Override
  public IotDevice getById(UUID id) {
    return iotDeviceRepository.findById(id)
        .orElseThrow(() -> new IotDeviceNotFoundException(id));
  }

  @Override
  public ResponseEntity<?> delete(UUID id) {
    IotDevice iotDevice = getById(id);
    iotDeviceRepository.delete(iotDevice);

    return ResponseEntity.ok().build();
  }
}
