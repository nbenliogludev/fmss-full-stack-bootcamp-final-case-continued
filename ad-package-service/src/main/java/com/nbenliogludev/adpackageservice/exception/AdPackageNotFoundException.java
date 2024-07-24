package com.nbenliogludev.adpackageservice.exception;

public class AdPackageNotFoundException extends RuntimeException {
    public AdPackageNotFoundException(String message) {
        super(message);
    }
}