package com.nbenliogludev.adservice.client.adPackage.service;

import com.nbenliogludev.adservice.client.adPackage.AdPackageClient;
import com.nbenliogludev.adservice.client.adPackage.dto.request.AdPackageUpdateRequest;
import com.nbenliogludev.adservice.client.adPackage.dto.response.AdPackageResponse;
import com.nbenliogludev.adservice.dto.response.RestResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
@Slf4j
public class AdPackageService {

    private final AdPackageClient adPackageClient;

    public ResponseEntity<RestResponse<AdPackageResponse>> getAdPackageByUserId(Long userId) {
        ResponseEntity<RestResponse<AdPackageResponse>> response = adPackageClient.getAdPackageByUserId(userId);
        return response;
    }

    public ResponseEntity<RestResponse<AdPackageResponse>> updateAdPackage(Long id, AdPackageUpdateRequest request) {
        ResponseEntity<RestResponse<AdPackageResponse>> response = adPackageClient.updateAdPackage(id, request);
        return response;
    }
}
