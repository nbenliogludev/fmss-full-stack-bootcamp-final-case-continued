package com.nbenliogludev.adservice.service;

import com.nbenliogludev.adservice.dto.request.AdCreateRequest;
import com.nbenliogludev.adservice.dto.request.AdUpdateRequest;
import com.nbenliogludev.adservice.dto.response.AdResponse;

import java.util.List;

public interface AdService {
    AdResponse createAd(AdCreateRequest request);

    List<AdResponse> getAllAds();

    AdResponse getAdById(Long id);
    List<AdResponse> getAdByUserId(Long id);

    AdResponse updateAd(Long id, AdUpdateRequest userDetails);

    void deleteAd(Long id);
}
