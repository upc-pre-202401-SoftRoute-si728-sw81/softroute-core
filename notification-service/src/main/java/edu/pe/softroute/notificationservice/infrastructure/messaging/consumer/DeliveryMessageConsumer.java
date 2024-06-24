package edu.pe.softroute.notificationservice.infrastructure.messaging.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pe.softroute.notificationservice.application.internal.services.EmailService;
import edu.pe.softroute.notificationservice.infrastructure.messaging.consumer.dto.NotificationDto;
import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryMessageConsumer {

  private final EmailService emailService;
  private final ObjectMapper objectMapper;

  @RabbitListener(queues = "notification.queue")
  public void handleMessage(String message) throws JsonProcessingException, MessagingException, UnsupportedEncodingException {
    NotificationDto notificationDto = objectMapper.readValue(message, NotificationDto.class);

    String subject = "Your Package Has Been Delivered!";

    String body = """
        Dear %s,

        We are pleased to inform you that your package with package code %s has been 
        successfully delivered to %s at %s.

        Thank you for choosing our service.

        Best regards,
        %s
        """.formatted(
        notificationDto.getOwnerName(),
        notificationDto.getPackageCode(),
        notificationDto.getDestination(),
        notificationDto.getDeliveryTime(),
        notificationDto.getCompanyName()
    );

    emailService.sendEmail(notificationDto.getCompanyName(), notificationDto.getOwnerEmail(), subject, body);
  }
}
