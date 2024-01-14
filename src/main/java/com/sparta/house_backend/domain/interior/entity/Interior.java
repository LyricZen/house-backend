package com.sparta.house_backend.domain.interior.entity;

import com.sparta.house_backend.domain.interior.dto.InteriorCreateRequest;
import com.sparta.house_backend.domain.interior.dto.InteriorUpdateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "interior")
@Getter
@NoArgsConstructor
public class Interior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interiorId;
    @Column(name = "interior_title")
    private String interiorTitle;
    @Column(name = "interior_contents")
    private String interiorContents;
    @Column(name = "interior_img")
    private String interiorImg;
    @Setter
    @Column
    private Integer interiorCount = 0;

//    public Interior(InteriorCreateRequest request) {
//        this.interiorTitle = request.getInteriorTitle();
//        this.interiorContents = request.getInteriorContents();
//        this.interiorImg = request.getInteriorImg();
//    }

    public Interior(InteriorCreateRequest request, String file) {
        this.interiorTitle = request.getInteriorTitle();
        this.interiorContents = request.getInteriorContents();
        this.interiorImg = file;
    }

    public Interior update(InteriorUpdateRequest request, String file) {
        this.interiorTitle = request.getInteriorTitle();
        this.interiorContents = request.getInteriorContents();
        this.interiorImg = file;
        return this;
    }

}
