package com.example.Ex03Autowired;

import org.springframework.stereotype.Component;

@Component("kakaoCard")
public class KakaoCard implements ICard{
    @Override
    public void buy(String itemName) {
        System.out.println("Kakao카드로"+itemName+"을 샀다.");
    }
}
