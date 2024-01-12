package com.sparta.house_backend.interior.controller;

import com.sparta.house_backend.interior.dto.InteriorCreateRequest;
import com.sparta.house_backend.interior.dto.InteriorResponse;
import com.sparta.house_backend.interior.dto.InteriorUpdateRequest;
import com.sparta.house_backend.interior.service.InteriorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InteriorController {

    private final InteriorService interiorService;

    // 0.인테리어 전체 조회

    // 1. 인테리어 등록
    @PostMapping("/interior")
    public ResponseEntity<InteriorResponse> createInterior(@RequestBody InteriorCreateRequest request) {
        InteriorResponse responseDto = interiorService.createInterior(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 2. 선택한 인테리어 조회
    @GetMapping("/interior/{interiorId}")
    public ResponseEntity<InteriorResponse> getInterior(@PathVariable Long interiorId) {
        InteriorResponse response = interiorService.getInterior(interiorId);
        return ResponseEntity.ok(response);
    }

    // 3. 선택한 인테리어 수정
    @PutMapping("/interior/{interiorId}")
    public ResponseEntity<InteriorResponse> updateInterior(@PathVariable Long interiorId, @RequestBody InteriorUpdateRequest request) {
        InteriorResponse response = interiorService.updateInterior(interiorId, request);
        return ResponseEntity.ok(response);
    }


}
