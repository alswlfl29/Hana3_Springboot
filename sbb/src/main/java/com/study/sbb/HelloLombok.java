package com.study.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HelloLombok {
    private final String hello;
    private final int lombok;

    public static void main(String[] args){
        HelloLombok helloLombok = new HelloLombok("hello",5);

        System.out.println(helloLombok.getHello());
        System.out.println(helloLombok.getLombok());
    }
}
