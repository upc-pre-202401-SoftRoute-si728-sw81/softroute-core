package edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.consumer.dto;

import lombok.Data;

@Data
public class IotGpsDto {
  private String shipmentCode;
  private Double latitude;
  private Double longitude;
}
