package com.study.Pr07LoginJoin;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
    private final List<Member> memberList = new ArrayList<>();

    public void save(Member member){
        memberList.add(member);
    }

    public List<Member> getMembers() {
        return new ArrayList<>(memberList);
    }
}