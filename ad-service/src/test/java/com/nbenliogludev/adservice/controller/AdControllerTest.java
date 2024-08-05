package com.nbenliogludev.adservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbenliogludev.adservice.dto.request.AdCreateRequest;
import com.nbenliogludev.adservice.dto.request.AdUpdateRequest;
import com.nbenliogludev.adservice.dto.response.AdResponse;
import com.nbenliogludev.adservice.enums.AdStatus;
import com.nbenliogludev.adservice.service.AdService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdController.class)
public class AdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdService adService;

    @Test
    public void getAllAds_shouldReturnAds_whenAdsExist() throws Exception {
        // Arrange
        AdResponse ad1 = new AdResponse(1L, "Ad 1", "Description 1", BigDecimal.valueOf(1000), AdStatus.IN_REVIEW);
        AdResponse ad2 = new AdResponse(2L, "Ad 2", "Description 2", BigDecimal.valueOf(2000), AdStatus.IN_REVIEW);
        List<AdResponse> ads = Arrays.asList(ad1, ad2);
        given(adService.getAllAds()).willReturn(ads);

        // Act & Assert
        mockMvc.perform(get("/api/v1/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(ads.size()))
                .andExpect(jsonPath("$.data[0].id").value(ad1.id()))
                .andExpect(jsonPath("$.data[0].title").value(ad1.title()))
                .andExpect(jsonPath("$.data[1].id").value(ad2.id()))
                .andExpect(jsonPath("$.data[1].title").value(ad2.title()));
    }



    @Test
    public void createAd_shouldCreateAd_andReturnAd() throws Exception {
        Long userId = 1L;
        // Arrange
        AdCreateRequest request = new AdCreateRequest(userId, "New Ad", "New Description", BigDecimal.valueOf(1000));
        AdResponse createdAd = new AdResponse(1L, request.title(), request.description(), BigDecimal.valueOf(1000), AdStatus.IN_REVIEW);
        given(adService.createAd(request)).willReturn(createdAd);

        // Act & Assert
        mockMvc.perform(post("/api/v1/ads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.title").value(request.title()));
    }


    @Test
    public void updateAd_shouldUpdateAd_andReturnUpdatedAd() throws Exception {
        // Arrange
        Long adId = 1L;
        AdUpdateRequest request = new AdUpdateRequest("Updated Ad", "Updated Description", BigDecimal.valueOf(1000), AdStatus.IN_REVIEW);
        AdResponse updatedAd = new AdResponse(adId, request.title(), request.description(), BigDecimal.valueOf(1000), AdStatus.IN_REVIEW);
        given(adService.updateAd(adId, request)).willReturn(updatedAd);

        // Act & Assert
        mockMvc.perform(put("/api/v1/ads/{id}", adId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value(request.title()));
    }

    @Test
    public void deleteAd_shouldDeleteAd() throws Exception {
        // Arrange
        Long adId = 1L;

        // Act & Assert
        mockMvc.perform(delete("/api/v1/ads/{id}", adId))
                .andExpect(status().isNoContent());
    }

    // Utility method to convert object to JSON string
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
