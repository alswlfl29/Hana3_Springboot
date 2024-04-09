<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>ex11</title>
  </head>
  <body>
    EL(JSTL)에서 스크립트릿 변수에 접근<br />
    <% String name = "김진사"; %>
    <c:set var="nameEL" value="<%= name %>" />
    nameEL: ${ nameEL } <br /><br />

    스크립트릿에서 JSTL 변수에 접근<br />
    <c:set var="nameEL2" value="홍두깨" />
    <% String nameScriptlet = (String)pageContext.getAttribute("nameEL");
    //document.writeln() out.append("nameEL2" + nameScriptlet); %>
    <br /><br />

    JS에서 JSTL변수에 접근<br />
    <c:set var="nameEL3" value="사임당" />
    <script>
      window.onload = function () {
        var name_js = '<c:out value="${nameEL3}" />';
        document.body.append(name_js);
      };
    </script>
    <br /><br />

    JSTL에서 JS변수에 접근 불가<br />
    <!-- input hidden에 같은 곳에 데이터를 내려준다. -->
    <!-- JS 변수는 JSTL의 EL구문에서 사용할 수 없다. -->
  </body>
</html>
