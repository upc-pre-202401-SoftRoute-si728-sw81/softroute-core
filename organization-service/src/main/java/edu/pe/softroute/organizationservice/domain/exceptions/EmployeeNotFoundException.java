package edu.pe.softroute.organizationservice.domain.exceptions;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
  public EmployeeNotFoundException(UUID id) {
    super("Employee with id " + id + " not found");
  }
}
