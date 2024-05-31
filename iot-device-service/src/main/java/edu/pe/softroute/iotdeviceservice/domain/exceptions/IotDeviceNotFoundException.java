package edu.pe.softroute.iotdeviceservice.domain.exceptions;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IotDeviceNotFoundException extends RuntimeException{

  public IotDeviceNotFoundException(UUID id) {
    super("IoT Device with id " + id + " not found");
  }
}
