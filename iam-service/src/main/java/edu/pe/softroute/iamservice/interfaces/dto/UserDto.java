package edu.pe.softroute.iamservice.interfaces.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

  private UUID id;

  private String firstName;

  private String lastName;

  private String dni;

  private LocalDate birthDate;

  private String phoneNumber;

  private String email;

  private UUID companyId;

  private List<String> roles;
}
