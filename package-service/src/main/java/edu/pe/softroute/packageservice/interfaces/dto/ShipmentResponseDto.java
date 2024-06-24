package edu.pe.softroute.packageservice.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.pe.softroute.packageservice.domain.models.entities.Location;
import edu.pe.softroute.packageservice.domain.models.entities.ShipmentCarrier;
import edu.pe.softroute.packageservice.domain.models.enums.ShipmentStatus;
import edu.pe.softroute.packageservice.infrastructure.google.services.maps.decoders.Point;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShipmentResponseDto {

  private UUID id;

  private String encodedPolyline;

  private String code;

  private ShipmentCarrier carrier;

  private int numPackages;

  private Location location;

  private List<Point> destinations;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime updatedAt;

  private ShipmentStatus status;
}
