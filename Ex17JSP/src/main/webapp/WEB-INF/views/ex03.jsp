<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>JSP 심볼들</title>
  </head>
  <body>
    <%-- 페이지 지시어 <%@ %> --%> <br />
    <%-- 출력문(표현식) <%= %> --%> <br />
    <%-- 전역변수,함수 선언부 <%! %> --%><br />
    <%-- 자바 코드 영역 - 스크립트릿 scriptlet --%> <br />
    <%-- <% %> --%> <br />
    <%! int globalVar = 10; public int sum( int a, int b ){ return a + b; } %>
    <% //스크립트릿 : JSP안의 Java 코드 String title = "java coding!";
    System.out.println( title ); %>
    <!-- 표현식 -->
    전역변수 : <%= globalVar %> <br />
    타이틀 문자열 : <%= title %> <br />
    합계 함수 : <%= sum(10, 20) %>
  </body>
</html>
