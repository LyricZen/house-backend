package com.sparta.house_backend.interior.dto;

import lombok.Getter;

@Getter
public class InteriorUpdateRequest {
    private String interiorTitle;
    private String interiorContents;
    private String interiorImg;
}
