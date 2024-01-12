package com.sparta.house_backend.interior.entity;

import com.sparta.house_backend.interior.dto.InteriorCreateRequest;
import com.sparta.house_backend.interior.dto.InteriorUpdateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "interior")
@Getter
@NoArgsConstructor
public class Interior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interiorId;
    @Column
    private String interiorTitle;
    @Column
    private String interiorContents;
    @Column
    private String interiorImg;
    @Column
    private Integer interiorCount;

    public Interior(InteriorCreateRequest request) {
        this.interiorTitle = request.getInteriorTitle();
        this.interiorContents = request.getInteriorContents();
        this.interiorImg = request.getInteriorImg();
    }

    public Interior update(InteriorUpdateRequest request) {
        this.interiorTitle = request.getInteriorTitle();
        this.interiorContents = request.getInteriorContents();
        this.interiorImg = request.getInteriorImg();
        return this;
    }


}
