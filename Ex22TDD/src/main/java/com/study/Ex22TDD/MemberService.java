package com.study.Ex22TDD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//              (디스패처 서블릿)
// 클라이언트 ---> Front Controller ---> Controller1,2,3
//      ---> Service(로직) ---> Repository(DAO) ---> Entity
//      ---> DBMS

// 클라 <--- View Resolver(html render) <--- Front Controller

@Service // 비즈니스 로직을 수행하는 클래스에 적용 -> 회원관련 로직
public class MemberService {
    // @Autowired
    // Member member;

    public int loginAction(Member member){
        // DAO(레파지토리) 클래스를 이용한 DB 검색
        if(member.getLoginId().equals("hong") &&
            member.getLoginPw().equals("1234")){
            return 1; // 성공
        }else{
            return 0; // 실패
        }
    }
}
