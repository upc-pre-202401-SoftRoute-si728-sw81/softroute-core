package edu.pe.softroute.packageservice.infrastructure.google.services.maps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteOnlyDestinationReq {
  private OriginDestination origin;
  private OriginDestination destination;
  private String travelMode;
}
