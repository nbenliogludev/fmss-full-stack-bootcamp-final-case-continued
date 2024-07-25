package com.nbenliogludev.logaggregationservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author nbenliogludev
 */
@Document(collection = "error_logs")
@Builder
@Getter
@Setter
public class ErrorLog {

    @Id
    private String id;

    private String service;
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String stackTrace;
}
