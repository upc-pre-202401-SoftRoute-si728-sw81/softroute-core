package edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.producer;

import edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.consumer.dto.IotGpsDto;
import edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.producer.dto.NotificationDto;
import edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.producer.dto.NotificationReq;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationMessageProducer {

  public static final String NOTIFICATION_DIRECT_EXCHANGE = "notification.direct-exchange";
  public static final String NOTIFICATION_ROUTING_KEY = "notification.routing-key";

  private final RabbitTemplate rabbitTemplate;

  public void sendNotificationMessage(NotificationReq req) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    NotificationDto data = NotificationDto.builder()
        .companyName(req.getCompanyName())
        .packageCode(req.getPackageCode())
        .ownerEmail(req.getOwnerEmail())
        .ownerName(req.getOwnerName())
        .destination(req.getDestination())
        .deliveryTime(req.getDeliveryTime().format(formatter))
        .build();


    rabbitTemplate.convertAndSend(NOTIFICATION_DIRECT_EXCHANGE, NOTIFICATION_ROUTING_KEY, data);
  }
}
