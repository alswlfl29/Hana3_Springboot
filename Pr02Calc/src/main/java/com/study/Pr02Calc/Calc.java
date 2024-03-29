package com.study.Pr02Calc;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Calc {
    private double result = 0;

    public int add(int num1, int num2){
        result = num1+num2;
        return (int) result;
    }

    public int minus(int num1, int num2){
        result = num1-num2;
        return (int) result;
    }

    public int multiply(int num1, int num2){
        result = num1*num2;
        return (int) result;
    }

    public double division(int num1, int num2){
        result = (double) num1/num2;
        return result;
    }
}
