package com.study.Pr07LoginJoin;

import lombok.Data;

@Data
public class ResDto {
    private String status;
    private String message;

    ResDto() {}


    ResDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // public static ResDto of(String status, String message) {
    //     return new ResDto(status, message);
    // }
}
