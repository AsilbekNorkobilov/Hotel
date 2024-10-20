package org.example.hotelmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.service.RefreshService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/refresh")
public class RefreshController {

    private final RefreshService refreshService;

    @Operation(summary = "refresh token",description = "Endpoint will renew access token")
    @GetMapping
    public HttpEntity<?> refresh(){
        String token = refreshService.refresh();
        return ResponseEntity.ok(token);
    }
}
