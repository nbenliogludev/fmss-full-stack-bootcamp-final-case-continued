package com.nbenliogludev.adservice.rabbitmq.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbenliogludev.adservice.dto.ErrorLogDTO;
import com.nbenliogludev.adservice.dto.InfoLogDTO;
import com.nbenliogludev.adservice.exception.LogProducerException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Author: nbenliogludev
 */
@Service
@RequiredArgsConstructor
public class LogProducer {

    @Value("${rabbitmq.queue.error-log}")
    private String errorLogQueue;

    @Value("${rabbitmq.queue.info-log}")
    private String infoLogQueue;

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void produceErrorLog(ErrorLogDTO errorLogDTO) {
        try {
            // Serialize the errorLogDTO and send it to the errorLogQueue
            String errorLogJson = objectMapper.writeValueAsString(errorLogDTO);

            // Send the errorLogJson to the errorLogQueue
            rabbitTemplate.convertAndSend(errorLogQueue, errorLogJson);

        } catch (Exception e) {
            throw new LogProducerException("Failed to produce error log", e);
        }
    }

    public void produceInfoLog(InfoLogDTO infoLogDTO) {
        try {
            // Serialize the infoLogDTO and send it to the infoLogQueue
            String infoLogJson = objectMapper.writeValueAsString(infoLogDTO);

            // Send the infoLogJson to the infoLogQueue
            rabbitTemplate.convertAndSend(infoLogQueue, infoLogJson);

        } catch (Exception e) {
            throw new LogProducerException("Failed to produce info log", e);
        }
    }
}
