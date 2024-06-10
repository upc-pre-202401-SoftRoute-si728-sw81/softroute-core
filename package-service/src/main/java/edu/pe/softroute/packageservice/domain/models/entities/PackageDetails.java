package edu.pe.softroute.packageservice.domain.models.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackageDetails {

  private Double weight;

  private Double height;

  private Double width;

  private Double length;
}
