package com.study.Pr07LoginJoin;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Data
@Component
public class Member {
    private String username;
    private String password;
    private String email;
    private LocalDate joinDate;

    @Builder
    private Member(String username, String password, String email, LocalDate joinDate){
        this.username = username;
        this.password = password;
        this.email = email;
        this.joinDate = joinDate;
    }

    public boolean isDuplicateName(String name){
        return this.username.equals(name);
    }

    public boolean isExistMember(String name, String password){
        return isDuplicateName(name) && this.password.equals(password);
    }
}