package com.sparta.house_backend.domain.interior.dto;

import com.sparta.house_backend.domain.interior.entity.Interior;
import lombok.Getter;

@Getter
public class InteriorResponse {
    private Long interiorId;
    private String interiorTitle;
    private String interiorContents;
    private String interiorImg;
    private Integer interiorCount;

    public InteriorResponse(Interior interior) {
        this.interiorId = interior.getInteriorId();
        this.interiorTitle = interior.getInteriorTitle();
        this.interiorContents = interior.getInteriorContents();
        this.interiorImg = interior.getInteriorImg();
        this.interiorCount = interior.getInteriorCount();
    }
}
