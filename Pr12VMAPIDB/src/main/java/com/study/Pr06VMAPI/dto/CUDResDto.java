package com.study.Pr06VMAPI.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CUDResDto {
    private String status;
    private String message;

    public CUDResDto(String status, String message){
        this.status=status;
        this.message=message;
    }
}
