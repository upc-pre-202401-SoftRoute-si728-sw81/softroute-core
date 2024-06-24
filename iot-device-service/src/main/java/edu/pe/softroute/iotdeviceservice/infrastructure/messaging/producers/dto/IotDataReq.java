package edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IotDataReq {

  @NotNull
  private String shipmentCode;

  @NotNull
  private String packageCode;

  @NotNull
  private Double humidity;

  @NotNull
  private Double temperature;

}
