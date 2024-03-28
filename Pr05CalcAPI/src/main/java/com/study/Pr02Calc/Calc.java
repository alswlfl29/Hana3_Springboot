package com.study.Pr02Calc;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Calc {
    private int number1;
    private int number2;
    private int result;
    private double divResult;

    public void add(int num1, int num2){
        number1=num1;
        number2=num2;
        result = num1+num2;
    }

    public void minus(int num1, int num2){
        number1=num1;
        number2=num2;
        result = num1-num2;
    }

    public void multiply(int num1, int num2){
        number1=num1;
        number2=num2;
        result = num1*num2;
    }

    public void division(int num1, int num2){
        number1=num1;
        number2=num2;
        divResult = (double) num1/num2;
    }
}
