package edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IotDataSentDto {

  private String trackingNumber;

  private Double latitude;

  private Double longitude;
}
