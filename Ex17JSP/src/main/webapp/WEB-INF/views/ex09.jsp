<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ page trimDirectiveWhitespaces="true" %>

<jsp:useBean id="users" scope="request" class="com.study.Ex17JSP.Users" />

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>ex09</title>
  </head>
  <body>
    <!-- EL(Expression Language) 표현 언어 -->
    <!-- JSP 2.3, EL 3.0, 서블릿(다이나믹웹모듈) 4.0 -->
    <!-- JDK 11, 톰캣 9.0 -->

    <!-- JSP표현식(값 출력)이 복잡하기 때문에 쉽게 사용하려고 -->
    <!-- 나온 것이 EL이다. -->

    <!-- 서버단에서 JSP 파일을 번역하는 순서 -->
    <!-- 자바빈(.java) -> EL/JSTL -> 스크립트릿 -> HTML/CSS/JS -->

    <!-- 클라단에서 HTML이 번역되는 순서 -->
    <!-- HTML/CSS -> JS -->

    <!-- 스크립트릿 -->
    <% users.setAddress("서울"); %>
    <!-- 표현식 -->
    <%= users.getAddress() %>
    <!-- EL -->
    ${ users.getAddress() }

    <!-- 표현식: 반복문 -->
    <% for(int i=0;i<5;i+=1){ %> i: <%= i %> <br />
    <% } %>

    <!-- EL: 반복문 -->
    <!-- EL에서는 스크립트릿 변수를 바로 출력할 수 없음. -->
    <!-- JSTL(Ctag Lib)로 변수를 출력한다. -->
    <% for(int i=0; i<5; i++){ %> i: ${ i } <br />
    <% } %>
  </body>
</html>
