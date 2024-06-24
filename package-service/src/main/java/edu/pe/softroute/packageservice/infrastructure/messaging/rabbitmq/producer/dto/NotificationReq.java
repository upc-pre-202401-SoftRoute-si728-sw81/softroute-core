package edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.producer.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationReq {

  @NotNull
  private String companyName;

  @NotNull
  private String packageCode;

  @NotNull
  private String ownerName;

  @NotNull
  private String ownerEmail;

  @NotNull
  private String destination;

  @NotNull
  private LocalDateTime deliveryTime;
}
