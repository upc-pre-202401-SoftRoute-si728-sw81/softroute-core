package edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers;

import edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto.IotDataReq;
import edu.pe.softroute.iotdeviceservice.infrastructure.messaging.producers.dto.IotDataSentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static edu.pe.softroute.iotdeviceservice.infrastructure.messaging.configuration.RabbitMQConfiguration.IOT_DIRECT_EXCHANGE;
import static edu.pe.softroute.iotdeviceservice.infrastructure.messaging.configuration.RabbitMQConfiguration.IOT_ROUTING_KEY;

@Slf4j
@Component
@RequiredArgsConstructor
public class IotDataProducer {

  private final RabbitTemplate rabbitTemplate;

  public void sendMessage(IotDataReq req) {

    IotDataSentDto dataSentDto = IotDataSentDto.builder()
        .trackingNumber(req.getTrackingNumber())
        .latitude(req.getLatitude())
        .longitude(req.getLongitude()).build();

    rabbitTemplate.convertAndSend(IOT_DIRECT_EXCHANGE, IOT_ROUTING_KEY, dataSentDto);
    log.info("Message with [{}] send to [{}] exchange with routing key [{}]", dataSentDto , IOT_DIRECT_EXCHANGE, IOT_ROUTING_KEY);
  }
}
