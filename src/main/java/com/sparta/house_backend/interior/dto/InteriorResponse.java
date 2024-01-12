package com.sparta.house_backend.interior.dto;

import com.sparta.house_backend.interior.entity.Interior;
import lombok.Getter;

@Getter
public class InteriorResponse {
    private Long interiorId;
    private String interiorTitle;
    private String interiorContents;
    private String interiorImg;
    private Integer interiorCount;

    public InteriorResponse(Interior createInterior) {
        this.interiorId = createInterior.getInteriorId();
        this.interiorTitle = createInterior.getInteriorTitle();
        this.interiorContents = createInterior.getInteriorContents();
        this.interiorImg = createInterior.getInteriorImg();
        this.interiorCount = createInterior.getInteriorCount();
    }
}
