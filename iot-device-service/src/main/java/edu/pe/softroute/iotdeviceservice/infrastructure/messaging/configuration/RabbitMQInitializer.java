package edu.pe.softroute.iotdeviceservice.infrastructure.messaging.configuration;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQInitializer implements CommandLineRunner {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Override
    public void run(String... args) throws Exception {
        rabbitAdmin.initialize();
    }
}