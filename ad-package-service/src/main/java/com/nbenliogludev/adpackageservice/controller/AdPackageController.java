package com.nbenliogludev.adpackageservice.controller;

import com.nbenliogludev.adpackageservice.dto.request.AdPackageCreateRequest;
import com.nbenliogludev.adpackageservice.dto.request.AdPackageUpdateRequest;
import com.nbenliogludev.adpackageservice.dto.response.AdPackageResponse;
import com.nbenliogludev.adpackageservice.dto.response.RestResponse;
import com.nbenliogludev.adpackageservice.exception.AdPackageNotFoundException;
import com.nbenliogludev.adpackageservice.service.AdPackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nbenliogludev
 */
@RestController
@RequestMapping("/api/v1/adPackages")
@RequiredArgsConstructor
@Tag(name = "Ad Packages Controller", description = "Provides operations for managing ad ad packages")
public class AdPackageController {

    private final AdPackageService adPackageService;

    @Operation(summary = "Get All Ad Packages", description = "Retrieves a list of all ad packages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of ads retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdPackageResponse.class)))
    })
    @GetMapping
    public ResponseEntity<RestResponse<List<AdPackageResponse>>> getAllAds() {
        List<AdPackageResponse> users = adPackageService.getAllAdPackages();
        return ResponseEntity.ok(RestResponse.of(users));
    }

    @Operation(summary = "Create Ad Package", description = "Creates a new ad package")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ad package created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdPackageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<RestResponse<AdPackageResponse>> createAdPackage(@RequestBody @Valid AdPackageCreateRequest request) {

        AdPackageResponse createdAd = adPackageService.createAdPackage(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(createdAd));
    }

    @Operation(summary = "Get ad package by Id", description = "Retrieves a ad package by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ad package retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdPackageResponse.class))),
            @ApiResponse(responseCode = "404", description = "Ad package not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<AdPackageResponse>> getAdPackageById(@PathVariable Long id) {
        AdPackageResponse user = adPackageService.getAdPackageById(id);
        return ResponseEntity.ok(RestResponse.of(user));
    }

    @Operation(summary = "Get Ad Package by User ID", description = "Retrieves an ad package by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ad package retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Ad package not found")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<RestResponse<AdPackageResponse>> getAdPackageByUserId(@PathVariable Long userId) {
        try {
            AdPackageResponse adPackage = adPackageService.getAdPackageByUserId(userId);
            return ResponseEntity.ok(RestResponse.of(adPackage));
        } catch (AdPackageNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RestResponse.error(null));
        }
    }

    @Operation(summary = "Update Ad Package", description = "Updates an existing ad package")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ad Package updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdPackageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Ad Package not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<AdPackageResponse>> updateAdPackage(@PathVariable Long id, @RequestBody @Valid AdPackageUpdateRequest request) {

        AdPackageResponse updatedAdPackage = adPackageService.updateAdPackage(id, request);
        return ResponseEntity.ok(RestResponse.of(updatedAdPackage));
    }

    @Operation(summary = "Delete Ad Package", description = "Deletes a ad package by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ad package deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Ad package not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteAdPackage(@PathVariable Long id) {

        adPackageService.deleteAdPackage(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(RestResponse.empty());
    }

}
