package com.study.Pr07LoginJoin;

import lombok.Data;

@Data
public class ResDto {
    private String status;
    private String message;

    public ResDto(String status, String message){
        this.status=status;
        this.message=message;
    }
}
