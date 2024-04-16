package com.study.Ex22TDD;

import org.junit.jupiter.api.*;

public class LifeCycleTest {
    @Test
    @DisplayName("테스트 코드 1")
    void testMethod01(){
        System.out.println("테스트 코드1");
    }


    @Test
    @DisplayName("테스트 코드 2 - 비활성화")
    @Disabled
    void testMethod02(){
        System.out.println("테스트 코드2");
    }

    @BeforeAll // 테스트 클래스 기준으로 테스트 메소드들이 실행되기 전에 한 번 실행
    static void beforeAll(){
        System.out.println("beforeAll");
    }

    @AfterAll // 테스트 클래스 기준으로 테스트 메소드들이 실행된 후에 한 번 실행 // 리소스를 정리하는 개념으로 사용
    static void afterAll() {
        System.out.println("afterAll");
    }

    @BeforeEach // 각 테스트 메소드가 실행되기 전에 매번 실행
    void beforeEach(){
        System.out.println("beforeEach");
    }

    @AfterEach // 각 테스트 메소드가 실행된 후에 매번 실행
    void afterEach(){
        System.out.println("afterEach");
    }
}
