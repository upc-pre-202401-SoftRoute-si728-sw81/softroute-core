package edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.producer.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDto {

  private String companyName;

  private String packageCode;

  private String ownerName;

  private String ownerEmail;

  private String destination;

  private String deliveryTime;
}
