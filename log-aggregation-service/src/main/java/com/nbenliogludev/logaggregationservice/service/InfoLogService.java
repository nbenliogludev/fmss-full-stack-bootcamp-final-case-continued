package com.nbenliogludev.logaggregationservice.service;

import com.nbenliogludev.logaggregationservice.dto.InfoLogDTO;

import java.util.List;

/**
 * @author nbenliogludev
 */
public interface InfoLogService {

    List<InfoLogDTO> getAllInfoLogs();

    void createInfoLog(InfoLogDTO infoLog);
}
