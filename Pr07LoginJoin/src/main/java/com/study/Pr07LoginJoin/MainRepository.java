package com.study.Pr07LoginJoin;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MainRepository {
    public static List<Member> memberList = new ArrayList<>();

    public void save(Member member){
        memberList.add(member);
    }
}
