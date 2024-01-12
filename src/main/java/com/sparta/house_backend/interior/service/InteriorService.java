package com.sparta.house_backend.interior.service;

import com.sparta.house_backend.interior.dto.InteriorCreateRequest;
import com.sparta.house_backend.interior.dto.InteriorResponse;
import com.sparta.house_backend.interior.dto.InteriorUpdateRequest;
import com.sparta.house_backend.interior.entity.Interior;
import com.sparta.house_backend.interior.repository.InteriorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InteriorService {

    private final InteriorRepository interiorRepository;

    public InteriorResponse createInterior(InteriorCreateRequest requsetDto) {
        Interior interior = new Interior(requsetDto);
        Interior createInterior = interiorRepository.save(interior);
        InteriorResponse interiorResponse = new InteriorResponse(createInterior);
        return interiorResponse;
    }

    public InteriorResponse getInterior(Long interiorId) {
        Interior interior = interiorRepository.findById(interiorId)
                .orElseThrow(IllegalAccessError::new);
        return new InteriorResponse(interior);
    }

    @Transactional
    public InteriorResponse updateInterior(Long interiorId, InteriorUpdateRequest request) {
        Interior interior = interiorRepository.findById(interiorId)
                .orElseThrow(IllegalArgumentException::new);
        Interior updateInterior = interior.update(request);
        return new InteriorResponse(updateInterior);
    }
}
