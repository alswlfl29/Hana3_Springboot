package com.study.Pr07LoginJoin;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
    private static final String MANAGER_NAME = "admin";
    private static final String MANAGER_PW = "1234";
    private final List<Member> memberList = new ArrayList<>();

    public void save(Member member){
        memberList.add(member);
    }
    public List<Member> getMembers(){
        return new ArrayList<>(memberList);
    }

    public boolean isManager(String name, String password){
        return MANAGER_NAME.equals(name) && MANAGER_PW.equals(password);
    }

    public void update(int index, Member member){
        memberList.set(index, member);
    }

    public void delete(int index){
        memberList.remove(index);
    }
}
