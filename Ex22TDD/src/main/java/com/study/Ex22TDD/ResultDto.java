package com.study.Ex22TDD;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDto {
    // { "status": "ok", "message": "로그인 성공" }
    private String status;
    private String message;
}
