package edu.pe.softroute.packageservice.application.internal.outboundservices.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {

  private UUID id;

  private String firstName;

  private String lastName;

  private LocalDate birthDate;

  private String dni;

  private String phoneNumber;

  private String email;
}
