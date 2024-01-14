package com.sparta.house_backend.domain.s3;

import com.amazonaws.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileUploadController {
    
    private final S3FileUploadService s3FileUploadService;
    
    @PostMapping("/upload")
    public ResponseEntity<String> saveFile(@RequestPart("file") MultipartFile file) throws IOException {
        String url = s3FileUploadService.saveFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }
}
