package com.nbenliogludev.logaggregationservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbenliogludev.logaggregationservice.dto.ErrorLogDTO;
import com.nbenliogludev.logaggregationservice.dto.InfoLogDTO;
import com.nbenliogludev.logaggregationservice.service.ErrorLogService;
import com.nbenliogludev.logaggregationservice.service.InfoLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author nbenliogludev
 */
@Service
@RequiredArgsConstructor
public class LogConsumer {

    private final ErrorLogService errorLogService;
    private final InfoLogService infoLogService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${rabbitmq.queue.error-log}")
    public void consumeErrorLog(String payload) {
        try {
            ErrorLogDTO errorLogDto = objectMapper.readValue(payload, ErrorLogDTO.class);
            errorLogService.createErrorLog(errorLogDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "${rabbitmq.queue.info-log}")
    public void consumeInfoLog(String payload) {
        try {
            InfoLogDTO infoLogDto = objectMapper.readValue(payload, InfoLogDTO.class);
            infoLogService.createInfoLog(infoLogDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
