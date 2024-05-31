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
  public static final String IOT_QUEUE = "iot.queue";
  public static final String IOT_ROUTING_KEY = "iot.routing-key";

  @Bean
  public DirectExchange iotDirectExchange() {
    return new DirectExchange(IOT_DIRECT_EXCHANGE);
  }

  @Bean
  public Queue iotQueue() {
    return new Queue(IOT_QUEUE);
  }

  @Bean
  public Binding iotBinding() {
    return BindingBuilder.bind(iotQueue())
        .to(iotDirectExchange())
        .with(IOT_ROUTING_KEY);
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
    RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
    rabbitAdmin.setAutoStartup(true);
    return rabbitAdmin;
  }
}
