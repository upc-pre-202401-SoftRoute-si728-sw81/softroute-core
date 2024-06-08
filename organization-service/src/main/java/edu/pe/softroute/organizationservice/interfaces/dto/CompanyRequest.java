package edu.pe.softroute.organizationservice.interfaces.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CompanyRequest {

  @NotEmpty
  private String name;

  @Length(min = 11, max = 11)
  private String ruc;
}
