package com.nbenliogludev.logaggregationservice.service.impl;

import com.nbenliogludev.logaggregationservice.dto.ErrorLogDTO;
import com.nbenliogludev.logaggregationservice.dto.InfoLogDTO;
import com.nbenliogludev.logaggregationservice.entity.ErrorLog;
import com.nbenliogludev.logaggregationservice.entity.InfoLog;
import com.nbenliogludev.logaggregationservice.repository.ErrorLogRepository;
import com.nbenliogludev.logaggregationservice.service.ErrorLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nbenliogludev
 */
@Service
@RequiredArgsConstructor
public class ErrorLogServiceImpl implements ErrorLogService {

    private final ErrorLogRepository errorLogRepository;

    @Override
    public void createErrorLog(ErrorLogDTO errorLogDto) {

        // Todo: use MapStruct
        ErrorLog errorLogEntity = ErrorLog.builder()
                .service(errorLogDto.service())
                .timestamp(errorLogDto.timestamp())
                .status(errorLogDto.status())
                .error(errorLogDto.error())
                .message(errorLogDto.message())
                .stackTrace(errorLogDto.stackTrace())
                .build();

        errorLogRepository.save(errorLogEntity);
    }

    @Override
    public List<ErrorLogDTO> getAllErrorLogs() {

        // Todo: use MapStruct
        return errorLogRepository.findAll().stream()
                .map(errorLog -> ErrorLogDTO.builder()
                        .service(errorLog.getService())
                        .timestamp(errorLog.getTimestamp())
                        .status(errorLog.getStatus())
                        .error(errorLog.getError())
                        .message(errorLog.getMessage())
                        .stackTrace(errorLog.getStackTrace())
                        .build())
                .toList();
    }
}
