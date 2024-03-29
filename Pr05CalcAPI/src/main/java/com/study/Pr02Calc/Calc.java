package com.study.Pr02Calc;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Calc {
    private double result = 0;

    public double calculation(ReqDto reqDto){
        int intNum1 = reqDto.getNum1();
        int intNum2 = reqDto.getNum2();
        if(reqDto.getOperation().equals("add")){
            this.result = this.add(intNum1,intNum2);
        }else if(reqDto.getOperation().equals("minus")){
            this.result = this.minus(intNum1,intNum2);
        }else if(reqDto.getOperation().equals("multiply")){
            this.result = this.multiply(intNum1,intNum2);
        }else{
            this.result = this.division(intNum1,intNum2);
        }
        return this.result;
    }

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
