package edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IotDataReq {

  @NotNull
  private String trackingNumber;

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

}
