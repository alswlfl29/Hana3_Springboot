package com.example.Ex03Autowired;

import org.springframework.stereotype.Component;

// 원래 빈의 이름 "bCCard"
@Component("cardBC") // 빈의 이름을 직접 지정할 수 있다.
public class BCCard implements ICard{
    @Override
    public void buy(String itemName) {
        System.out.println("BC카드로  s"+itemName+"을 샀다.");
    }
}
