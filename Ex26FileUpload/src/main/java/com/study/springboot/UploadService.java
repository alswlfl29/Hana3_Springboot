package com.study.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class UploadService {
    @Value("${spring.servlet.multipart.location}")
    private String CKImageFolder;
    @Value("/upload/")
    private String cKImagePath;

    public CKResponse ckImageUpload(MultipartFile image){
        if(image != null && !image.isEmpty()){
            // 파일명 바꾸기
            String imageName = UUID.randomUUID() + "-" + image.getOriginalFilename();
            // 파일 객체 생성
            File file = new File(CKImageFolder, imageName);
            // 이미지를 파일로 저장
            try{
                image.transferTo(file);
            }catch (Exception e){
                e.printStackTrace();
            }
            return new CKResponse(1, imageName, cKImagePath + imageName);
        }
        return null;
    }
}
