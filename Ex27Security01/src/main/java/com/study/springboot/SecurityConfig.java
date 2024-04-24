package com.study.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 웹보안 활성화를 위한 어노테이션
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http    // HTTP 요청에 대한 보안설정을 시작한다.
                .authorizeHttpRequests((auth) -> auth
                    // root 밑의 모든 경로에 대한 모든 요청을 허가한다.
                    // 이건 보안이 풀린것!! -> 좋지 않음
                    .requestMatchers(new AntPathRequestMatcher("/**"))
                    .permitAll())
                // 로그인 인증에 대한 설정 시작
                .formLogin((formLogin) -> formLogin
                        // 로그인 페이지를 /loginForm URI로 하겠다.
                        .loginPage("/loginForm")
                        // 로그인 액션 URI
                        .loginProcessingUrl("/loginAction")
                        // 로그인 성공시 넘어갈 URI 지정
                        .defaultSuccessUrl("/")
                        // 로그인 페이지를 모든 사용자에게 허용한다.
                        .permitAll())
        ;
        return http.build();
    }

}
