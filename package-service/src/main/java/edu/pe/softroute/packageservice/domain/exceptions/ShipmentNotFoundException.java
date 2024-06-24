package edu.pe.softroute.packageservice.domain.exceptions;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShipmentNotFoundException extends RuntimeException {
  public ShipmentNotFoundException(UUID id) {
    super("Shipment with id " + id + " not found");
  }
}
