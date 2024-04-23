package com.study.springboot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CKResponse {
    // 업로드한 이미지 개수
    private Integer uploaded;
    // 파일명 - UUID나 년월일시분초+랜덤수를 이용하여 파일명이 겹치지 않도록 한다.
    private String fileName;
    // 이미지를 볼 수 있는 URL
    private String url;
}
