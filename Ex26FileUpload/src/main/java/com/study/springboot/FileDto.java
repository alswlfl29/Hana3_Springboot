package com.study.springboot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
    private String uuid; // unique한 파일 이름을 가진 필드
    private String fileName;
    private String contentType;

}
