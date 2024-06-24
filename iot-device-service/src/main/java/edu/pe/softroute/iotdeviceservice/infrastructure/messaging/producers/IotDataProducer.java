package edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers;

import edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto.IotDataReq;
import edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto.IotDataDto;
import edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto.IotGpsDto;
import edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto.IotGpsReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static edu.pe.softroute.iotdeviceservice.infrastructure.messaging.configuration.RabbitMQConfiguration.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class IotDataProducer {

  private final RabbitTemplate rabbitTemplate;

  public void sendLocationMessage(IotGpsReq req) {

    IotGpsDto data = IotGpsDto.builder()
        .shipmentCode(req.getShipmentCode())
        .latitude(req.getLatitude())
        .longitude(req.getLongitude()).build();

    rabbitTemplate.convertAndSend(IOT_DIRECT_EXCHANGE, IOT_GPS_ROUTING_KEY, data);
    log.info("Message with [{}] send to [{}] exchange with routing key [{}]", data , IOT_DIRECT_EXCHANGE, IOT_GPS_ROUTING_KEY);

  }

  public void sendDataMessage(IotDataReq req) {

    IotDataDto data = IotDataDto.builder()
        .shipmentCode(req.getShipmentCode())
        .packageCode(req.getPackageCode())
        .humidity(req.getHumidity())
        .temperature(req.getTemperature())
        .build();

    rabbitTemplate.convertAndSend(IOT_DIRECT_EXCHANGE, IOT_DATA_ROUTING_KEY, data);
    log.info("Message with [{}] send to [{}] exchange with routing key [{}]", data , IOT_DIRECT_EXCHANGE, IOT_DATA_ROUTING_KEY);
  }
}
