package com.sparta.house_backend.domain.interior.controller;

import com.sparta.house_backend.domain.interior.dto.InteriorResponse;
import com.sparta.house_backend.domain.interior.dto.InteriorUpdateRequest;
import com.sparta.house_backend.domain.interior.service.InteriorService;
import com.sparta.house_backend.domain.interior.dto.InteriorCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InteriorController {

    private final InteriorService interiorService;

    // 1. 인테리어 등록
    @PostMapping("/interior")
    public ResponseEntity<InteriorResponse> createInterior(InteriorCreateRequest request) {
        InteriorResponse responseDto = interiorService.createInterior(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 1.2) 인테리어 등록(사진 추가)
//    @PostMapping("/interior")
//    public ResponseEntity<InteriorResponse> createInterior(InteriorCreateRequest request) {
//        InteriorResponse responseDto = interiorService.createInterior(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
//    }

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

    // 4.인테리어 전체 조회
    @GetMapping("/interior")
    public ResponseEntity<List<InteriorResponse>> getInteriorList() {
        List<InteriorResponse> responseList = interiorService.getInteriorList();
        return ResponseEntity.ok(responseList);
    }

    // 5.선택한 인테리어 삭제
    @DeleteMapping("/interior/{interiorId}")
    public void deleteInterior(@PathVariable Long interiorId) {
        interiorService.deleteInterior(interiorId);
    }

}
