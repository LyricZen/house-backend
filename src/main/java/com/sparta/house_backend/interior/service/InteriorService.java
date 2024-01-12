package com.sparta.house_backend.interior.service;

import com.sparta.house_backend.interior.dto.InteriorCreateRequest;
import com.sparta.house_backend.interior.dto.InteriorResponse;
import com.sparta.house_backend.interior.dto.InteriorUpdateRequest;
import com.sparta.house_backend.interior.entity.Interior;
import com.sparta.house_backend.interior.repository.InteriorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InteriorService {

    private final InteriorRepository interiorRepository;

    // 1. 인테리어 등록
    public InteriorResponse createInterior(InteriorCreateRequest requsetDto) {
        Interior interior = new Interior(requsetDto);
        Interior createInterior = interiorRepository.save(interior);
        InteriorResponse interiorResponse = new InteriorResponse(createInterior);
        return interiorResponse;
    }

    // 2. 선택한 인테리어 조회
    public InteriorResponse getInterior(Long interiorId) {
        Interior interior = interiorRepository.findById(interiorId)
                .orElseThrow(IllegalAccessError::new);
        return new InteriorResponse(interior);
    }

    // 3. 선택한 인테리어 수정
    @Transactional
    public InteriorResponse updateInterior(Long interiorId, InteriorUpdateRequest request) {
        Interior interior = interiorRepository.findById(interiorId)
                .orElseThrow(IllegalArgumentException::new);
        Interior updateInterior = interior.update(request);
        return new InteriorResponse(updateInterior);
    }

    // 4. 인테리어 리스트 전체 조회
    public List<InteriorResponse> getInteriorList() {
        List<Interior> interiorList = interiorRepository.findAll();

        List<InteriorResponse> interiorResponseList = interiorList
                .stream().map(InteriorResponse::new).toList();

        return interiorResponseList;
    }

    // 5. 선택한 인테리어 삭제
    public void deleteInterior(Long interiorId) {
        Interior interior = interiorRepository.findById(interiorId)
                .orElseThrow(IllegalArgumentException::new);
        interiorRepository.delete(interior);
    }


}
