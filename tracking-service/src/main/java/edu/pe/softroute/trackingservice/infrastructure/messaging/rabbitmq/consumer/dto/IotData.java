package edu.pe.softroute.trackingservice.infrastructure.messaging.rabbitmq.consumer.dto;

import lombok.Data;

@Data
public class IotData {
  private String trackingNumber;
  private Double latitude;
  private Double longitude;
}
