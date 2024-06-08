package edu.pe.softroute.trackingservice.domain.models.entities;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location  {

  private String street;

  private String district;

  private String province;

  private String country;

  private Double latitude;

  private Double longitude;

  private LocalDateTime timestamp;
}
