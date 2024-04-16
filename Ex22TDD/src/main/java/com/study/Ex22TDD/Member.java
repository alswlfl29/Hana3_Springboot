package com.study.Ex22TDD;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    private String loginId;
    private String loginPw;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(loginId, member.loginId) && Objects.equals(loginPw, member.loginPw);
    }

}
