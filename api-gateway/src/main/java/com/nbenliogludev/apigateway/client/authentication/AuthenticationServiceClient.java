package com.nbenliogludev.apigateway.client.authentication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authentication-service", url = "lb://AUTHENTICATION-SERVICE")
public interface AuthenticationServiceClient {

    @GetMapping("/api/v1/auth/validate")
    boolean validateToken(@RequestHeader("Authorization") String token);
}
