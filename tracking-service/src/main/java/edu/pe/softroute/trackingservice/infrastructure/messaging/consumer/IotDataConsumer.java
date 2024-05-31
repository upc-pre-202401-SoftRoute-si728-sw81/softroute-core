package edu.pe.softroute.trackingservice.infrastructure.messaging.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pe.softroute.trackingservice.domain.models.entities.Location;
import edu.pe.softroute.trackingservice.domain.models.entities.Tracking;
import edu.pe.softroute.trackingservice.domain.services.TrackingService;
import edu.pe.softroute.trackingservice.infrastructure.messaging.consumer.dto.IotData;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IotDataConsumer {

  private final TrackingService trackingService;
  private final SimpMessagingTemplate messagingTemplate;

  @RabbitListener(queues = "iot.queue", autoStartup = "false")
  public void handleMessage(String message) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    IotData data = objectMapper.readValue(message, IotData.class);

    System.out.println("Received message from queue " + "iot.queue" + ": " + data);

    Location location = new Location(data.getLatitude(), data.getLongitude(), LocalDateTime.now());

    Tracking tracking = trackingService.updateLocationByTrackingNumber(data.getTrackingNumber(), location);
    messagingTemplate.convertAndSend("/topic/tracking", objectMapper.writeValueAsString(tracking));
  }
}
