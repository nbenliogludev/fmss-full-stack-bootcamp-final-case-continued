package com.nbenliogludev.adpackageservice.service;

import com.nbenliogludev.adpackageservice.dto.request.AdPackageCreateRequest;
import com.nbenliogludev.adpackageservice.dto.request.AdPackageUpdateRequest;
import com.nbenliogludev.adpackageservice.dto.response.AdPackageResponse;

import java.util.List;

public interface AdPackageService {
    AdPackageResponse createAdPackage(AdPackageCreateRequest request);
    List<AdPackageResponse> getAllAdPackages();
    AdPackageResponse getAdPackageById(Long id);
    AdPackageResponse getAdPackageByUserId(Long id);
    AdPackageResponse updateAdPackage(Long id, AdPackageUpdateRequest request);
    void deleteAdPackage(Long id);
}
