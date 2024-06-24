package edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IotDataDto {

  private String shipmentCode;

  private String packageCode;

  private Double humidity;

  private Double temperature;
}
