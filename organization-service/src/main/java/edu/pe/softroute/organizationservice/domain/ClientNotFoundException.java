package edu.pe.softroute.organizationservice.domain;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends RuntimeException {
  public ClientNotFoundException(UUID id) {
    super("Client with id " + id + " not found");
  }
}
