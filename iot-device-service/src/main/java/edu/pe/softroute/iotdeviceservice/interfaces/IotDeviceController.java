package edu.pe.softroute.iotdeviceservice.interfaces;

import edu.pe.softroute.iotdeviceservice.domain.models.entities.IotDevice;
import edu.pe.softroute.iotdeviceservice.domain.services.IotDeviceService;
import edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.IotDataProducer;
import edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto.IotDataReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @PostMapping("/data")
  public void sendData(@Valid @RequestBody IotDataReq req) {
    iotDataProducer.sendMessage(req);
  }

}
