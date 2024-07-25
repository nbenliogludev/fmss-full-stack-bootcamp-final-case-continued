package com.nbenliogludev.logaggregationservice.repository;

import com.nbenliogludev.logaggregationservice.entity.InfoLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author nbenliogludev
 */
public interface InfoLogRepository extends MongoRepository<InfoLog, String> {
}
