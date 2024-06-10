package edu.pe.softroute.iamservice.infrastructure.jwt.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtInfoDto {
  private String userId;
  private String companyId;
}
