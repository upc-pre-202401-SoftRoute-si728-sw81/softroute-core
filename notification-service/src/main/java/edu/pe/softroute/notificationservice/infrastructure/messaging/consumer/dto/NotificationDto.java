package edu.pe.softroute.notificationservice.infrastructure.messaging.consumer.dto;

import lombok.Data;

@Data
public class NotificationDto {

  private String companyName;

  private String packageCode;

  private String ownerName;

  private String ownerEmail;

  private String destination;

  private String deliveryTime;
}
