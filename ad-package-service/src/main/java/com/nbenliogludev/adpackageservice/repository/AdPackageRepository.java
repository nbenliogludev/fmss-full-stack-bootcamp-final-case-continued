package com.nbenliogludev.adpackageservice.repository;

import com.nbenliogludev.adpackageservice.entity.AdPackage;
import com.nbenliogludev.adpackageservice.enums.AdPackageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * @author nbenliogludev
 */
public interface AdPackageRepository extends JpaRepository<AdPackage, Long> {
    Optional<AdPackage> findByUserId(Long userId);
    Optional<AdPackage> findByUserIdAndStatus(Long userId, AdPackageStatus status);
}

