package com.nbenliogludev.logaggregationservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue infoLogQueue() {
        return new Queue("infoLogQueue", true);
    }

    @Bean
    public Queue errorLogQueue() {
        return new Queue("errorLogQueue", true);
    }
}
