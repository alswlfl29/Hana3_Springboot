<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ page trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>ex15.jsp 문자열포맷(숫자, 날짜)</title>
  </head>
  <body>
    <!-- 스크립트릿 변수 <-> 표현식 -->
    <!-- JSTL 변수 <-> EL(표현언어) -->
    <!-- JS 변수 -->

    <!-- 숫자타입 -->
    <c:set var="price" value="1000" />
    <fmt:formatNumber var="numberType" type="number" value="${price}" />
    숫자: ${numberType}원 <br>
    <!-- maxFractionDigits: 소수점 최대치 -->
    <fmt:formatNumber var="numberType" type="currency" value="${price}"
    currencySymbol="₩" maxFractionDigits="0" />
    통화: ${numberType} <br>
    <fmt:formatNumber var="numberType" type="currency" value="${price}"
    currencySymbol="$" />
    통화: ${numberType} <br>
    <fmt:formatNumber var="numberType" type="percent" value="${price}" />
    퍼센트: ${numberType} <br>
    <fmt:formatNumber var="numberType" pattern="0000000.00" value="${price}" />
    패턴: ${numberType} <br>

    <!-- 날짜타입 -->
    <c:set var="now" value="<%= new java.util.Date() %>" />
    <fmt:formatDate value="${now}" type="date" dateStyle="full" /><br>
    <fmt:formatDate value="${now}" type="date" dateStyle="short" /><br>
    <fmt:formatDate value="${now}" type="time" /><br>
    <fmt:formatDate value="${now}" type="both"
     dateStyle="full" timeStyle="full" /><br>
    <fmt:formatDate value="${now}" pattern="z a h:mm" /><br>
    <fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" /><br>
    <!-- 날짜시간 문자열에서 파싱하기 -->
    <fmt:parseDate value="2024-04-09 11:53:20"
        pattern="yyyy-MM-dd HH:mm:ss" var="date" />
    ${date} <br>
    <fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss" /><br>
  </body>
</html>
