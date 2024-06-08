package edu.pe.softroute.organizationservice.interfaces.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDto {

  private UUID id;

  private String name;

  private String ruc;
}
