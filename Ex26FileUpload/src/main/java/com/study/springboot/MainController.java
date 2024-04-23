package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "upload"; // upload.html 응답
    }
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile[] uploadfile,
                         Model model) throws IOException {
        List<FileDto> list = new ArrayList<>();

        for(MultipartFile file: uploadfile){
            if(!file.isEmpty()){
                // FileDto 정보 생성
                FileDto dto = new FileDto(UUID.randomUUID().toString(),
                        file.getOriginalFilename(),
                        file.getContentType()); // jpg, png
                list.add(dto);

                File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
                // 전달한 내용을 실제 물리적인 파일로 저장한다.
                file.transferTo(newFileName);

                // DB에 파일이름을 저장한다.

            }
        }

        model.addAttribute("files", list);
        return "result"; // result.html 응답
    }

    @GetMapping("/ckeditor4")
    public String ckeditor4(){
        return "upload_ckeditor4";
    }

    @Autowired
    private UploadService uploadService;

    // ResponseEntity: HttpEntity를 상속받는 응답용 객체
    @PostMapping("/ckUpload")
    @ResponseBody
    public ResponseEntity<CKResponse> ckImageUpload(
            MultipartFile upload /* 파라미터이름 upload로 고정 */
    ){
        CKResponse ckResponse = uploadService.ckImageUpload(upload);

        // json 데이터로 응답
        // { "uploaded": 1, "fileName": "test.jpg", "url": "/img/test.jpg" }
        return ResponseEntity.ok(ckResponse);
    }

}
