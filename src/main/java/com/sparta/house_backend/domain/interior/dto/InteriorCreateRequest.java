package com.sparta.house_backend.domain.interior.dto;

import lombok.Getter;

@Getter
public class InteriorCreateRequest {
    private String interiorTitle;
    private String interiorContents;
    private String interiorImg;
}
