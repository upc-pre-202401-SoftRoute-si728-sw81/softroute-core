package edu.pe.softroute.iotdeviceservice.infrastructure.messaging.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
  public static final String IOT_DIRECT_EXCHANGE = "iot.direct-exchange";
  public static final String NOTIFICATION_DIRECT_EXCHANGE = "notification.direct-exchange";

  public static final String IOT_GPS_QUEUE = "iot-gps.queue";
  public static final String IOT_DATA_QUEUE = "iot-data.queue";
  public static final String NOTIFICATION_QUEUE = "notification.queue";

  public static final String IOT_GPS_ROUTING_KEY = "iot-gps.routing-key";
  public static final String IOT_DATA_ROUTING_KEY = "iot-data.routing-key";
  public static final String NOTIFICATION_ROUTING_KEY = "notification.routing-key";

  @Bean
  public DirectExchange iotDirectExchange() {
    return new DirectExchange(IOT_DIRECT_EXCHANGE);
  }

  @Bean
  public DirectExchange notificationDirectExchange() {
    return new DirectExchange(NOTIFICATION_DIRECT_EXCHANGE);
  }

  @Bean
  public Queue iotDataQueue() {
    return new Queue(IOT_DATA_QUEUE);
  }


  @Bean
  public Queue iotGpsQueue() {
    return new Queue(IOT_GPS_QUEUE);
  }

  @Bean
  public Queue notificationQueue() {
    return new Queue(NOTIFICATION_QUEUE);
  }

  @Bean
  public Binding iotGpsBinding() {
    return BindingBuilder.bind(iotGpsQueue())
        .to(iotDirectExchange())
        .with(IOT_GPS_ROUTING_KEY);
  }

  @Bean
  public Binding iotDataBinding() {
    return BindingBuilder.bind(iotDataQueue())
        .to(iotDirectExchange())
        .with(IOT_DATA_ROUTING_KEY);
  }

  @Bean
  public Binding notificationBinding() {
    return BindingBuilder.bind(notificationQueue())
        .to(notificationDirectExchange())
        .with(NOTIFICATION_ROUTING_KEY);
  }

  @Bean
  public MessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(converter());
    return rabbitTemplate;
  }

  @Bean
  public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
    return new RabbitAdmin(connectionFactory);
  }
}
