package com.eventmanagement.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

}
