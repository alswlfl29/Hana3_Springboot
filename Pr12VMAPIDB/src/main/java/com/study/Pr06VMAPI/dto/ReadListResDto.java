package com.study.Pr06VMAPI.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReadListResDto<T> {
    private List<T> lists;
    private String status;
    private String message;

    public ReadListResDto (List<T> lists, String status, String message){
        this.lists = lists;
        this.status = status;
        this.message = message;
    }
}
