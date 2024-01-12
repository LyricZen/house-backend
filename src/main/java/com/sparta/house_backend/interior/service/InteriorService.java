package com.sparta.house_backend.interior.service;

import com.sparta.house_backend.interior.dto.InteriorCreateRequest;
import com.sparta.house_backend.interior.dto.InteriorResponse;
import com.sparta.house_backend.interior.entity.Interior;
import com.sparta.house_backend.interior.repository.InteriorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
