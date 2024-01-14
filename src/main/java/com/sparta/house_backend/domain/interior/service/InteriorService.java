package com.sparta.house_backend.domain.interior.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.sparta.house_backend.domain.interior.dto.InteriorResponse;
import com.sparta.house_backend.domain.interior.dto.InteriorUpdateRequest;
import com.sparta.house_backend.domain.interior.entity.Interior;
import com.sparta.house_backend.domain.interior.repository.InteriorRepository;
import com.sparta.house_backend.domain.interior.dto.InteriorCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InteriorService {

    private final InteriorRepository interiorRepository;
    private final ImageS3Service imageS3Service;

    // 1. 인테리어 등록
//    public InteriorResponse createInterior(InteriorCreateRequest requsetDto) {
//        Interior interior = new Interior(requsetDto);
//        Interior createInterior = interiorRepository.save(interior);
//        InteriorResponse interiorResponse = new InteriorResponse(createInterior);
//        return interiorResponse;
//    }


    // 7.2) 인테리어 등록 서비스 (사진 추가)
    @Transactional
    public ResponseEntity<InteriorResponse> createInterior(InteriorCreateRequest request, MultipartFile file) throws IOException {
        Interior interior = new Interior(request, imageS3Service.saveFile(file));
        Interior saveInterior = interiorRepository.save(interior);
        return ResponseEntity.status(HttpStatus.CREATED).body(new InteriorResponse(saveInterior));
    }

//    // 2. 선택한 인테리어 조회
//    @Transactional
//    public InteriorResponse getInterior(Long interiorId) {
//        Interior interior = interiorRepository.findById(interiorId)
//                .orElseThrow(IllegalArgumentException::new);
//
//        // 6. 선택한 인테리어 조회시 조회수 증가 기능
//        interior.setInteriorCount(interior.getInteriorCount() + 1);
//        return new InteriorResponse(interior);
//    }

    // 8.2) 선택한 인테리어 조회 서비스(사진 추가)
    @Transactional
    public ResponseEntity<InteriorResponse> getInterior(Long interiorId) {
        Interior interior = interiorRepository.findById(interiorId)
                .orElseThrow(IllegalArgumentException::new);

        // 6. 선택한 인테리어 조회시 조회수 증가 기능
        interior.setInteriorCount(interior.getInteriorCount() + 1);
        return ResponseEntity.ok(new InteriorResponse(interior));
    }

//    // 3. 선택한 인테리어 수정
//
//    @Transactional
//    public InteriorResponse updateInterior(Long interiorId, InteriorUpdateRequest request) {
//        Interior interior = interiorRepository.findById(interiorId)
//                .orElseThrow(IllegalArgumentException::new);
//        Interior updateInterior = interior.update(request);
//        return new InteriorResponse(updateInterior);
//    }

    // 9.2) 선택한 인테리어 수정
    @Transactional
    public ResponseEntity<InteriorResponse> updateInterior(Long interiorId, InteriorUpdateRequest request, MultipartFile newFile) throws IOException {
        Interior interior = interiorRepository.findById(interiorId)
                .orElseThrow(IllegalArgumentException::new);

        String updatedImgUrl = imageS3Service.updateFile(interior.getInteriorImg(), newFile);
        interior.update(request, updatedImgUrl);

        return ResponseEntity.ok(new InteriorResponse(interior));
    }


    // 4. 인테리어 리스트 전체 조회
    public List<InteriorResponse> getInteriorList() {
        List<Interior> interiorList = interiorRepository.findAll();

        List<InteriorResponse> interiorResponseList = interiorList
                .stream().map(InteriorResponse::new).toList();

        return interiorResponseList;
    }


//    // 5. 선택한 인테리어 삭제
//
//    public void deleteInterior(Long interiorId) {
//        Interior interior = interiorRepository.findById(interiorId)
//                .orElseThrow(IllegalArgumentException::new);
//        interiorRepository.delete(interior);
//    }

}
