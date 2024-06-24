package edu.pe.softroute.organizationservice.interfaces.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponseDto {

  private UUID id;

  private String firstName;

  private String lastName;

  private LocalDate birthDate;

  private String dni;

  private String phoneNumber;

  private String email;
}
