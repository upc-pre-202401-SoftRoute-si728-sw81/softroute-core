package edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.consumer.dto;

import lombok.Data;

@Data
public class IotDataDto {

  private String shipmentCode;

  private String packageCode;

  private Double humidity;

  private Double temperature;
}
