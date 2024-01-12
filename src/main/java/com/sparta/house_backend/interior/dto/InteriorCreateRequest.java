package com.sparta.house_backend.interior.dto;

import lombok.Getter;

@Getter
public class InteriorCreateRequest {
    private String interiorTitle;
    private String interiorContents;
    private String interiorImg;
}
