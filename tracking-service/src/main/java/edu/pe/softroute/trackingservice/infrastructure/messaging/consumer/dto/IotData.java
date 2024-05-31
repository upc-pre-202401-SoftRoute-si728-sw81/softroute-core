package edu.pe.softroute.trackingservice.infrastructure.messaging.consumer.dto;

import lombok.Data;

@Data
public class IotData {
  private String trackingNumber;
  private Double latitude;
  private Double longitude;
}
