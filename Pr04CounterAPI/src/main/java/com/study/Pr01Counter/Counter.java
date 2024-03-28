package com.study.Pr01Counter;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Counter {
    private int count;

    public Counter(){
        this.count=0;
    }
}