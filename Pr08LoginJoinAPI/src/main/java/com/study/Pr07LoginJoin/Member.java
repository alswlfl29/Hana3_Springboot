package com.study.Pr07LoginJoin;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private String username;
    private String password;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;

    public boolean checkDuplication(String name){
        return this.username.equals(name);
    }

    public boolean isMember(String name, String password){
        return checkDuplication(name) && this.password.equals(password);
    }
}