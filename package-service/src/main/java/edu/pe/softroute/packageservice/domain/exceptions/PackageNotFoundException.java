package edu.pe.softroute.packageservice.domain.exceptions;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PackageNotFoundException extends RuntimeException{
  public PackageNotFoundException(UUID id) {
    super("Package with id " + id + " not found");
  }
}
