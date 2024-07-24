package com.nbenliogludev.adservice.mapper;

import com.nbenliogludev.adservice.dto.request.AdCreateRequest;
import com.nbenliogludev.adservice.dto.response.AdResponse;
import com.nbenliogludev.adservice.entity.Ad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author nbenliogludev
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdMapper {

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "amount", target = "amount")
    Ad mapAdCreateRequestToAd(AdCreateRequest request);

    AdResponse mapToAdResponse(Ad ad);
}
