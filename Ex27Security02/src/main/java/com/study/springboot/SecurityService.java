package com.study.springboot;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityService implements UserDetailsService {
    // 사용자 아이디를 통해 사용자 정보와 권한을 스프링 시큐리티에 전달해준다.
    // CTRL + I (구현 메소드창 보기)
    // CTRL + O (오버라이드 메소드창 보기)

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 테스트로 ADMIN 권한/역할을 넣어주자
        authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        // username(아이디): hong
        // password(비번): "1234" 문자열을 Bcrypt 사이트(bcrypt-generator.com)에서 암호 생성해서 넣는다.
        return new User("hong", "$2a$16$GK2OpkijluatL/2WdKqNWO3in7c/aNwkS/ZiqAyaKKuzRirh1YkcK", authorities);
    }
}
