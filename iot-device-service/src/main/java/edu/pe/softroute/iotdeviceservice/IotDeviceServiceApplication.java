package edu.pe.softroute.iotdeviceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IotDeviceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotDeviceServiceApplication.class, args);
	}

}
