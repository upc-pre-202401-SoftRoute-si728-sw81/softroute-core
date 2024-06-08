package edu.pe.softroute.packageservice.domain.models.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackageOwner {

  private String names;

  private String surnames;

  private String dni;

  private String email;

  private String phoneNumber;
}
