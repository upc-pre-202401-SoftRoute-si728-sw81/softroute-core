package edu.pe.softroute.apigateway.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtInfoDto {
  private String userId;
  private String companyId;
}
