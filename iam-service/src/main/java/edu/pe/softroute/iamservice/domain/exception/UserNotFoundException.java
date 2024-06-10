package edu.pe.softroute.iamservice.domain.exception;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(UUID id) {
    super("User with id " + id + " not found");
  }

  public UserNotFoundException(String email) {
    super("User with email " + email + " not found");
  }
}
