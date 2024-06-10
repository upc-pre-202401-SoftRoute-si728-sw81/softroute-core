package edu.pe.softroute.packageservice.interfaces.dto;

import edu.pe.softroute.packageservice.domain.models.entities.PackageDetails;
import edu.pe.softroute.packageservice.domain.models.entities.PackageOwner;
import edu.pe.softroute.packageservice.domain.models.enums.PackageStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackageResponseDto {

  private UUID id;

  private String description;

  private PackageStatus status;

  private PackageDetails details;

  private PackageOwner owner;
}
