package com.nbenliogludev.logaggregationservice.service;

import com.nbenliogludev.logaggregationservice.dto.ErrorLogDTO;

import java.util.List;

/**
 * @author nbenliogludev
 */
public interface ErrorLogService {

    List<ErrorLogDTO> getAllErrorLogs();

    void createErrorLog(ErrorLogDTO errorLog);
}
