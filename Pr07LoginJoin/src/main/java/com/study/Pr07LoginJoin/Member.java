package com.study.Pr07LoginJoin;

import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.study.Pr07LoginJoin.MainRepository.memberList;

@Data
@Component
public class Member {

    private String username;
    private String password;
    private String email;
    private LocalDate joinDate;

    public boolean checkDuplication(String name){
        for(Member member:memberList){
            if(member.getUsername().equals(name)) {
                return true;
            }
        }
        return false;
    }
}