package edu.pe.softroute.packageservice.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.pe.softroute.packageservice.domain.models.entities.Location;
import edu.pe.softroute.packageservice.domain.models.entities.PackageDetails;
import edu.pe.softroute.packageservice.domain.models.entities.PackageOwner;
import edu.pe.softroute.packageservice.domain.models.enums.PackageStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackageResponseDto {

  private UUID id;

  private String code;

  private Double humidity;

  private Double temperature;

  private String destinationAddress;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime createdAt;

  private String description;

  private PackageStatus status;

  private PackageDetails details;

  private PackageOwner owner;
}
