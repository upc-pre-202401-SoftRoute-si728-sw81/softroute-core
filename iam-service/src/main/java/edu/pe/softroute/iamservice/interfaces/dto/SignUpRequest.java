package edu.pe.softroute.iamservice.interfaces.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class SignUpRequest {

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @NotEmpty
  private String dni;

  @NotNull
  private LocalDate birthDate;

  @NotEmpty
  private String phoneNumber;

  @NotEmpty
  @Email
  private String email;

  @NotEmpty
  private String password;

  private List<String> roles;
}
