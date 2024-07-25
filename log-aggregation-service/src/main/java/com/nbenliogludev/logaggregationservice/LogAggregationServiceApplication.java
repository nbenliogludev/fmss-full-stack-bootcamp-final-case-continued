package com.nbenliogludev.logaggregationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LogAggregationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogAggregationServiceApplication.class, args);
	}

}
