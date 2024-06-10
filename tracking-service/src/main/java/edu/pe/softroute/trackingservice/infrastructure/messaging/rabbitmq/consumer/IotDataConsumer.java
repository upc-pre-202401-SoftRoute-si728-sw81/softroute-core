package edu.pe.softroute.trackingservice.infrastructure.messaging.rabbitmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pe.softroute.trackingservice.domain.models.entities.Location;
import edu.pe.softroute.trackingservice.domain.models.entities.Tracking;
import edu.pe.softroute.trackingservice.domain.services.TrackingService;
import edu.pe.softroute.trackingservice.infrastructure.google.maps.decoders.Point;
import edu.pe.softroute.trackingservice.infrastructure.google.maps.services.GeoLocationService;
import edu.pe.softroute.trackingservice.infrastructure.google.maps.services.RoutesApiService;
import edu.pe.softroute.trackingservice.infrastructure.messaging.rabbitmq.consumer.dto.IotData;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IotDataConsumer {

  private final TrackingService trackingService;
  private final GeoLocationService geoLocationService;
  private final RoutesApiService routesApiService;
  private final SimpMessagingTemplate messagingTemplate;
  private final ObjectMapper objectMapper;

  @RabbitListener(queues = "iot.queue")
  public void handleMessage(String message) throws JsonProcessingException {

    IotData data = objectMapper.readValue(message, IotData.class);
    System.out.println("Received message from queue " + "iot.queue" + ": " + data);

    Point point = routesApiService.getCurrentStep(data.getTrackingNumber());
    System.out.println(point);

    data.setLatitude(point.getLat());
    data.setLongitude(point.getLng());

    Location location = geoLocationService.getLocation(data.getLatitude(), data.getLongitude());
    System.out.println("Location: " + location);

    Tracking tracking = trackingService.updateLocationByTrackingNumber(data.getTrackingNumber(), location);

    String messageTracking = objectMapper.writeValueAsString(tracking);
    messagingTemplate.convertAndSend("/topic/tracking/" + tracking.getTrackingNumber() , messageTracking);
  }
}
