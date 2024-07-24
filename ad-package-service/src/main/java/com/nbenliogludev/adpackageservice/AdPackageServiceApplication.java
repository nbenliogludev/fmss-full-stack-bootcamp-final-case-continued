package com.nbenliogludev.adpackageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdPackageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdPackageServiceApplication.class, args);
	}

}
