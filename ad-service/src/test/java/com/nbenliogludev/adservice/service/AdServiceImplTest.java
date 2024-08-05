package com.nbenliogludev.adservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nbenliogludev.adservice.dto.request.AdUpdateRequest;
import com.nbenliogludev.adservice.dto.response.AdResponse;
import com.nbenliogludev.adservice.entity.Ad;
import com.nbenliogludev.adservice.enums.AdStatus;
import com.nbenliogludev.adservice.exception.AdNotFoundException;
import com.nbenliogludev.adservice.mapper.AdMapper;
import com.nbenliogludev.adservice.repository.AdRepository;
import com.nbenliogludev.adservice.service.Impl.AdServiceImpl;
import com.nbenliogludev.adservice.util.AppLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class AdServiceImplTest {

    @InjectMocks
    private AdServiceImpl adService;

    @Mock
    private AdRepository adRepository;

    @Mock
    private AdMapper adMapper;

    @Mock
    private AppLogger appLogger; // Mock AppLogger using @Mock

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetAllAds_EmptyList() {
        // Arrange
        when(adRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<AdResponse> results = adService.getAllAds();

        // Assert
        assertTrue(results.isEmpty());
    }

    @Test
    void testGetAdById_Found() {
        // Arrange
        Long id = 1L;
        Ad ad = new Ad();
        ad.setId(id);
        ad.setUserId(1L);
        ad.setTitle("Title");
        ad.setDescription("Description");
        ad.setAmount(BigDecimal.valueOf(500));
        ad.setStatus(AdStatus.ACTIVE);

        when(adRepository.findById(id)).thenReturn(Optional.of(ad));
        when(adMapper.mapToAdResponse(ad)).thenReturn(new AdResponse(id, "Title", "Description", BigDecimal.valueOf(500), AdStatus.ACTIVE));

        // Act
        AdResponse result = adService.getAdById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.id());
    }

    @Test
    void testGetAdById_NotFound() {
        // Arrange
        Long id = 1L;
        when(adRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AdNotFoundException.class, () -> adService.getAdById(id));
    }

    @Test
    void testUpdateAd_NotFound() {
        // Arrange
        Long id = 1L;
        AdUpdateRequest request = new AdUpdateRequest("Updated Title", "Updated Description", BigDecimal.valueOf(600), AdStatus.ACTIVE);
        when(adRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AdNotFoundException.class, () -> adService.updateAd(id, request));
    }

    @Test
    void testDeleteAd_Success() {
        // Arrange
        Long id = 1L;
        doNothing().when(adRepository).deleteById(id);

        // Act
        adService.deleteAd(id);

        // Assert
        verify(adRepository, times(1)).deleteById(id);
    }
}
