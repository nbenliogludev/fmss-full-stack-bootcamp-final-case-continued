package com.nbenliogludev.adpackageservice.entity;

import com.nbenliogludev.adpackageservice.entity.common.Auditable;
import com.nbenliogludev.adpackageservice.enums.AdPackageStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ad_packages")
@Getter
@Setter
public class AdPackage extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "number_of_ads", nullable = false)
    private int numberOfAds = 10;

    @Column(name = "ads_used", nullable = false)
    private int adsUsed = 0;

    @Column(name = "validity_period", nullable = false)
    private final int validityPeriod = 30;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdPackageStatus status;

    // Constructor to set default values
    public AdPackage() {
        this.expirationDate = LocalDateTime.now().plusDays(validityPeriod);
        this.status = AdPackageStatus.ACTIVE;
    }
}
