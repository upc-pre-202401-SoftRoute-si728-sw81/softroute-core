package edu.pe.softroute.iamservice.interfaces.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticatedUserDto {

  private UUID id;

  private String email;

  private String token;
}
