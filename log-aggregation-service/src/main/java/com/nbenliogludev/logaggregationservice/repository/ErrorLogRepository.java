package com.nbenliogludev.logaggregationservice.repository;

import com.nbenliogludev.logaggregationservice.entity.ErrorLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author nbenliogludev
 */
public interface ErrorLogRepository extends MongoRepository<ErrorLog, String> {
}
