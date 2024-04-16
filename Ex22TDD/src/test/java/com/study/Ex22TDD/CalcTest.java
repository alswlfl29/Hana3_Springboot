package com.study.Ex22TDD;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// 단정함수(Assert)를 통해 메소드 4개가 정상 동작하는지 테스트하시오.
class CalcTest {
    static Calc calc = null;

    // BeforeAll 은 static 메소드
    @BeforeAll
    static void init(){
        System.out.println("Before All");
        if(calc == null){
            calc = new Calc();
        }
    }

    @Test
    @DisplayName("add 테스트")
    void add() {
        assertEquals(12, calc.add(10,2));
    }

    @Test
    @DisplayName("sub 테스트")
    void sub() {
        assertEquals(8, calc.sub(10,2));
    }

    @Test
    @DisplayName("mul 테스트")
    void mul() {
        assertEquals(20, calc.mul(10,2));
    }

    @Test
    @DisplayName("div 테스트")
    void div() {
        assertEquals(5, calc.div(10,2));
    }
}