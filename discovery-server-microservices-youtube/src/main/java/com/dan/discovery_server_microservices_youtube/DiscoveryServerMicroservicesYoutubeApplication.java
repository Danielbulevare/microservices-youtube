package com.dan.discovery_server_microservices_youtube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerMicroservicesYoutubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerMicroservicesYoutubeApplication.class, args);
	}

}
