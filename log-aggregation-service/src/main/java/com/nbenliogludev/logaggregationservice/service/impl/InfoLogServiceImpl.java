package com.nbenliogludev.logaggregationservice.service.impl;

import com.nbenliogludev.logaggregationservice.dto.InfoLogDTO;
import com.nbenliogludev.logaggregationservice.entity.InfoLog;
import com.nbenliogludev.logaggregationservice.repository.InfoLogRepository;
import com.nbenliogludev.logaggregationservice.service.InfoLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nbenliogludev
 */
@Service
@RequiredArgsConstructor
public class InfoLogServiceImpl implements InfoLogService {

    private final InfoLogRepository infoLogRepository;

    @Override
    public void createInfoLog(InfoLogDTO infoLog) {

        // Todo: use MapStruct
        InfoLog infoLogEntity = InfoLog.builder()
                .service(infoLog.service())
                .timestamp(infoLog.timestamp())
                .message(infoLog.message())
                .description(infoLog.description())
                .build();

        infoLogRepository.save(infoLogEntity);
    }

    @Override
    public List<InfoLogDTO> getAllInfoLogs() {

        // Todo: use MapStruct
        return infoLogRepository.findAll().stream()
                .map(infoLog -> InfoLogDTO.builder()
                        .service(infoLog.getService())
                        .timestamp(infoLog.getTimestamp())
                        .message(infoLog.getMessage())
                        .description(infoLog.getDescription())
                        .build())
                .toList();
    }
}
