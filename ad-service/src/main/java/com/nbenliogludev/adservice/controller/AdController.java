package com.nbenliogludev.adservice.controller;

import com.nbenliogludev.adservice.dto.request.AdCreateRequest;
import com.nbenliogludev.adservice.dto.request.AdUpdateRequest;
import com.nbenliogludev.adservice.dto.response.AdResponse;
import com.nbenliogludev.adservice.dto.response.RestResponse;
import com.nbenliogludev.adservice.service.AdService;
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
@RequestMapping("/api/v1/ads")
@RequiredArgsConstructor
@Tag(name = "Ad Controller", description = "Provides operations for managing ads")
public class AdController {

    private final AdService adService;

    @Operation(summary = "Get All Ads", description = "Retrieves a list of all ads")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of ads retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdResponse.class)))
    })
    @GetMapping
    public ResponseEntity<RestResponse<List<AdResponse>>> getAllAds() {
        System.out.println("Get All Ads");
        List<AdResponse> ads = adService.getAllAds();
        return ResponseEntity.ok(RestResponse.of(ads));
    }

    @Operation(summary = "Get ad by Id", description = "Retrieves a ad by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ad retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdResponse.class))),
            @ApiResponse(responseCode = "404", description = "Ad not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<AdResponse>> getAdById(@PathVariable Long id) {
        AdResponse user = adService.getAdById(id);
        return ResponseEntity.ok(RestResponse.of(user));
    }

    @Operation(summary = "Get ads  by user Id", description = "Retrieves a ads by user Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ad retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdResponse.class))),
            @ApiResponse(responseCode = "404", description = "Ad not found")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<RestResponse<List<AdResponse>>> getAdByUserId(@PathVariable Long userId) {
        List<AdResponse> ads = adService.getAdByUserId(userId);
        return ResponseEntity.ok(RestResponse.of(ads));
    }

    @Operation(summary = "Create Ad", description = "Creates a new ad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ad created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<RestResponse<AdResponse>> createAd(@RequestBody @Valid AdCreateRequest request) {

        AdResponse createdAd = adService.createAd(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(createdAd));
    }

    @Operation(summary = "Update Ad", description = "Updates an existing ad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ad updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Ad not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<AdResponse>> updateAd(@PathVariable Long id, @RequestBody @Valid AdUpdateRequest request) {

        AdResponse updatedAd = adService.updateAd(id, request);
        return ResponseEntity.ok(RestResponse.of(updatedAd));
    }

    @Operation(summary = "Delete Ad", description = "Deletes a ad by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ad deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Ad not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteAd(@PathVariable Long id) {

        adService.deleteAd(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(RestResponse.empty());
    }
}
