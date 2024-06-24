package edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IotGpsDto {

  private String shipmentCode;

  private Double latitude;

  private Double longitude;
}
