package com.study.Ex22TDD;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//단정(Assert) 메서드 : 테스트 케이스의 수행 결과를 판별하는 용도로 사용.
//종류
//assertArrayEquals(a, b) : 배열 a와 b가 일치함을 확인한다.
//assertEquals(a, b) : 객체 a와 b가 일치함을 확인한다. (객체에 정의되어 있는 equals를 통해 비교한다.)
//assertSame(a, b) : 객체 a와 b 가 같은 객체임을 확인 한다. (객체 자체를 비교한다. == )
//assertTrue(a) : 조건 a가 참인지를 확인한다.
//assertFalse(a) : 조건 a가 거짓인지를 확인한다.
//assertNotNull(a) : 객체 a가 null인지 확인한다.
//assertAll() : 모든 종류의 assert를 각각 다 실행한다. 중간에 멈추지 않음.

class Adder{
    public int add(int a, int b){
        return a+b;
    }
}

public class AssertTest {
    private final Adder adder = new Adder();
    private final String[] arr1 = {"a","b"};
    private final String[] arr2 = {"c","d"};

    @Test
    void assertTest(){
        assertArrayEquals(arr1,arr1);
        // assertArrayEquals(arr1,arr2);

        assertEquals(3,adder.add(1,2)); // 값 비교

        assertSame(adder, adder); // 주솟값 비교
        // assertSame(adder, arr1);

        assertTrue(10<20); // 조건절이 true일 때 통과
        // assertTrue(10>20);

        assertFalse(10>20); // 조건절이 false일 때 통과

        assertNotNull(adder); // null이 아니면 통과
        // assertNotNull(null); // fail

        assertAll(
                // 람다식 리스트
                // (condition, message): condition은 테스트할 내용, message는 출력할 내용
                () -> assertFalse(false,"Exception"),
                () -> {
                    Object obj = new Object();
                    assertNotNull(obj,"not null");
                }
        );
    }
}
