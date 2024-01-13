package com.sparta.house_backend.domain.interior.entity;

import com.sparta.house_backend.domain.interior.dto.InteriorCreateRequest;
import com.sparta.house_backend.domain.interior.dto.InteriorUpdateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Setter
    @Column
    private Integer interiorCount = 0;

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
