package edu.pe.softroute.packageservice.infrastructure.google.services.maps.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteOtherDestinationReq {
  private OriginDestination origin;
  private OriginDestination destination;
  private List<OriginDestination> intermediates;
  private String travelMode = "DRIVE";
}
