package com.sparta.house_backend.domain.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3FileUploadService {

    private final AmazonS3Client amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

//    private String dir = "/test";
//    private String defaultUrl = "https://project-2housebucket.s3.ap-northeast-2.amazonaws.com";

    public String saveFile(MultipartFile multipartFile) throws IOException {

        String originalFileName = multipartFile.getOriginalFilename();



        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucketName, originalFileName, multipartFile.getInputStream(), metadata);

        return amazonS3.getUrl(bucketName, originalFileName).toString();
    }
}