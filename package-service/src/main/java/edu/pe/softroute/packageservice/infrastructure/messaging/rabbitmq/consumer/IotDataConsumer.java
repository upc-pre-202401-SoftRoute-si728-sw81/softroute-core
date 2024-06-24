package edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pe.softroute.packageservice.application.internal.outboundservices.ExternalOrganizationService;
import edu.pe.softroute.packageservice.application.internal.outboundservices.dto.ClientResponseDto;
import edu.pe.softroute.packageservice.domain.models.entities.Package;
import edu.pe.softroute.packageservice.domain.models.entities.Shipment;
import edu.pe.softroute.packageservice.domain.models.enums.PackageStatus;
import edu.pe.softroute.packageservice.domain.services.PackageService;
import edu.pe.softroute.packageservice.domain.services.ShipmentService;
import edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.consumer.dto.IotDataDto;
import edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.consumer.dto.IotGpsDto;
import edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.producer.NotificationMessageProducer;
import edu.pe.softroute.packageservice.infrastructure.messaging.rabbitmq.producer.dto.NotificationReq;
import edu.pe.softroute.packageservice.interfaces.transform.PackageResponseDtoAssembler;
import edu.pe.softroute.packageservice.interfaces.transform.ShipmentResponseDtoAssembler;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IotDataConsumer {

  private final ExternalOrganizationService organizationService;
  private final NotificationMessageProducer notificationMessageProducer;
  private final ShipmentService shipmentService;
  private final PackageService packageService;
  private final SimpMessagingTemplate messagingTemplate;
  private final ObjectMapper objectMapper;

  @RabbitListener(queues = "iot-gps.queue")
  public void handleIotGpsMessage(IotGpsDto data) throws JsonProcessingException {
    System.out.println(data);
    Shipment shipment = shipmentService.move(data.getShipmentCode(), data.getLatitude(), data.getLongitude());
    String messageShipping = objectMapper.writeValueAsString(ShipmentResponseDtoAssembler.assemble(shipment));
    String deliveredPackageCode = shipmentService.checkoutPackageDelivered(shipment.getCode());
    if (!deliveredPackageCode.isEmpty()) {
      System.out.println("Creando mensaje");
      Package deliveredPackage = packageService.getByCode(deliveredPackageCode);
      ClientResponseDto response = organizationService.getClientById(deliveredPackage.getCustomerId());
      NotificationReq notificationReq = NotificationReq.builder()
          .ownerEmail(response.getEmail())
          .ownerName(response.getNames() + " " + response.getSurnames())
          .companyName("DIEGO SAC")
          .destination(deliveredPackage.getDestinationAddress())
          .packageCode(deliveredPackage.getCode())
          .deliveryTime(LocalDateTime.now())
          .build();
      System.out.println("Enviando mensaje");
      notificationMessageProducer.sendNotificationMessage(notificationReq);
      System.out.println("Mensaje enviado");
    }
    messagingTemplate.convertAndSend("/topic/shipment/" + shipment.getCode() , messageShipping);
  }

  @RabbitListener(queues = "iot-data.queue")
  public void handleIotDataMessage(IotDataDto data) throws JsonProcessingException {

    Package packageDto = packageService.updateTemperatureAndHumidity(data.getPackageCode(), data.getHumidity(), data.getTemperature());
    String messagePackage = objectMapper.writeValueAsString(PackageResponseDtoAssembler.assemble(packageDto));
    messagingTemplate.convertAndSend("/topic/shipment/" + data.getShipmentCode() + "/packages", messagePackage);
  }
}
