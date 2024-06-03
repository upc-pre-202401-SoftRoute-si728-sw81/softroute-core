package edu.pe.softroute.iamservice.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceValidationException extends RuntimeException {

  public ResourceValidationException(String message) {
    super(message);
  }
}
