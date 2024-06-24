package edu.pe.softroute.packageservice.application.internal.outboundservices.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {

  private UUID id;

  private String names;

  private String surnames;

  private String dni;

  private String email;

  private String phoneNumber;
}
