package com.study.Ex11MessageInter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class Config {
    //LocaleResolver 구현체
    //1.AcceptHeaderLocaleResolver : 웹 브라우저가 전송한 헤더의 Accept-Language 값을 기반으로 Locale을 선택한다.
    //                               setLocale() 메서드를 지원하지 않는다.
    //2.SessionLocaleResolver : 세션으로부터 Locale 정보를 구한다. 많이 사용.
    //                          setLocale() 메서드는 세션에 Locale 정보를 저장한다.-> 우리가 핸들링 가능
    //3.CookieLocaleResolver : 쿠키를 이용해서 Locale 정보를 구한다.
    //                         setLocale() 메서드는 쿠키에 Locale 정보를 저장한다.
    //4.FixedLocaleResolver : 웹 요청에 상관없이 특정한 Locale로 설정한다.
    //                        setLocale() 메서드를 지원하지 않는다.
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.KOREA);
        return sessionLocaleResolver;
    }
}
