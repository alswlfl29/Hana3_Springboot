package com.study.ProjectCompanyWeb.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String loginID;
    private String loginPW;
}
