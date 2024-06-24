package edu.pe.softroute.iotdeviceservice.interfaces;

import edu.pe.softroute.iotdeviceservice.domain.models.entities.IotDevice;
import edu.pe.softroute.iotdeviceservice.domain.services.IotDeviceService;
import edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.IotDataProducer;
import edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto.IotDataReq;
import edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto.IotGpsReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/iot-devices")
@RequiredArgsConstructor
public class IotDeviceController {

  private final IotDeviceService iotDeviceService;

  private final IotDataProducer iotDataProducer;

  @PostMapping
  public ResponseEntity<IotDevice> create() {
    return new ResponseEntity<>(iotDeviceService.create(), HttpStatus.OK);
  }

  @PostMapping("/location-data")
  public void sendLocationData(@Valid @RequestBody IotGpsReq req) {
    iotDataProducer.sendLocationMessage(req);
  }

  @PostMapping("/data")
  public void sendData(@Valid @RequestBody IotDataReq req) {
    iotDataProducer.sendDataMessage(req);
  }

}
