package com.sparta.house_backend.domain.interior.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class InteriorCreateRequest {
    private String interiorTitle;
    private String interiorContents;
    private MultipartFile file;
}
