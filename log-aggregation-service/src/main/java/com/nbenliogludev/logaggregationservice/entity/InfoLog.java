package com.nbenliogludev.logaggregationservice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author nbenliogludev
 */
@Document(collection = "info_logs")
@Builder
@Getter
@Setter
public class InfoLog {

    @Id
    private String id;

    private String service;
    private LocalDateTime timestamp;
    private String message;
    private String description;
}
