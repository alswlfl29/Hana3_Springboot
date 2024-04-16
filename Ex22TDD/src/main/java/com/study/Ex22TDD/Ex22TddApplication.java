package com.study.Ex22TDD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex22TddApplication {

	public static void main(String[] args) {
		// 사람이 직접 테스트하는 것 -> 좋지 않음(테스트 코드에서 작성하기)
		// Calc calc = new Calc();
		// System.out.println(calc.add(10,20));
		SpringApplication.run(Ex22TddApplication.class, args);
	}

}
