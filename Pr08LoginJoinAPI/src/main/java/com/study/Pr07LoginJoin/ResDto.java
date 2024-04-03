package com.study.Pr07LoginJoin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResDto {
    private String status;
    private String message;
}
