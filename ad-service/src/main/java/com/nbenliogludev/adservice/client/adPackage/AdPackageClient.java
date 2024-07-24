package com.nbenliogludev.adservice.client.adPackage;

import com.nbenliogludev.adservice.client.adPackage.dto.request.AdPackageUpdateRequest;
import com.nbenliogludev.adservice.client.adPackage.dto.response.AdPackageResponse;
import com.nbenliogludev.adservice.dto.response.RestResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "ad-package-service")
public interface AdPackageClient {

    @GetMapping("/api/v1/adPackages/user/{userId}")
    ResponseEntity<RestResponse<AdPackageResponse>> getAdPackageByUserId(@PathVariable Long userId);

    @PutMapping("/api/v1/adPackages/{id}")
    ResponseEntity<RestResponse<AdPackageResponse>> updateAdPackage(@PathVariable Long id, @RequestBody @Valid AdPackageUpdateRequest request);
}
