package com.sparta.house_backend.interior.controller;

import com.sparta.house_backend.interior.dto.InteriorCreateRequest;
import com.sparta.house_backend.interior.dto.InteriorResponse;
import com.sparta.house_backend.interior.service.InteriorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InteriorController {

    private final InteriorService interiorService;

    @PostMapping("/interior")
    public ResponseEntity<InteriorResponse> createInterior(@RequestBody InteriorCreateRequest request) {
        InteriorResponse responseDto = interiorService.createInterior(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
