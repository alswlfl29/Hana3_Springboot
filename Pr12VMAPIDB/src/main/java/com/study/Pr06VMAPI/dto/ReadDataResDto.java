package com.study.Pr06VMAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadDataResDto<T> {
    private T data;
    private String status;
    private String message;

    public ReadDataResDto(T data, String status, String message){
        this.data=data;
        this.status=status;
        this.message=message;
    }

}
