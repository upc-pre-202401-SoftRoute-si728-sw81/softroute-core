package edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IotGpsReq {

  @NotNull
  private String shipmentCode;

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;
}
