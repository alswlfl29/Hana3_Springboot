<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>자판기</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
      th,
      td {
        border: 1px solid rgb(220 38 38);
        padding: 10px;
      }
      td {
        font-weight: 400;
      }
      /*switch*/
      label {
        position: relative;
        display: inline-block;
        width: 45px;
        height: 20px;
      }
      label input {
        opacity: 0;
        width: 0;
        height: 0;
      }
      .slider {
        position: absolute;
        cursor: pointer;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: transparent;
        border: 2px solid white;
        -webkit-transition: 0.4s;
        transition: 0.4s;
      }
      .slider:before {
        position: absolute;
        content: "";
        height: 13px;
        width: 13px;
        bottom: 1.5px;
        left: 2px;
        background-color: white;
        -webkit-transition: 0.4s;
        transition: 0.4s;
      }
      input:checked + .slider {
        background-color: rgb(252, 74, 56);
        right: -2px;
      }
      input:focus + .slider {
        box-shadow: 0 0 1px rgb(252, 74, 56);
      }
      input:checked + .slider:before {
        -webkit-transform: translateX(26px);
        -ms-transform: translateX(26px);
        transform: translateX(26px);
      }
      .slider.round {
        border-radius: 34px;
      }
      .slider.round::before {
        border-radius: 50%;
      }
    </style>
  </head>
  <body>
    <div class="w-1/2 m-auto mt-3 p-4 border border-4 border-black bg-red-700">
      <h2
        class="bg-white text-red-700 text-center text-xl italic p-2 rounded-t-lg mb-5"
      >
        <spring:message code="page.title" />
      </h2>
      <div class="flex justify-between items-center mb-5">
        <div class="p-2 bg-white text-red-700 text-center rounded-r-lg">
          ▽ <spring:message code="page.listName" />
        </div>
        <div class="flex justify-center items-center">
          <span class="text-center text-white font-semibold mr-1">한글</span>
          <label>
            <input id="checkBtn" type="checkbox" />
            <span class="slider round"></span>
          </label>
          <span class="text-center text-white font-semibold ml-1">ENG</span>
        </div>

        <button>-</button>
      </div>
      <div class="bg-white text-center font-semibold mb-5 p-3">
        <c:if test="${empty productList}">
          <span><spring:message code="page.noList" /></span>
        </c:if>

        <c:if test="${not empty productList}">
          <table class="border border-red-600 w-full">
            <thead>
              <tr>
                <th class="text-red-600">
                  <spring:message code="page.list.no" />
                </th>
                <th><spring:message code="page.list.name" /></th>
                <th>
                  <spring:message code="page.list.price" />
                </th>
                <th>
                  <spring:message code="page.list.limitDate" />
                </th>
                <th>
                  <spring:message code="page.list.update" />
                </th>
                <th>
                  <spring:message code="page.list.delete" />
                </th>
              </tr>
            </thead>
            <tbody>
              <c:forEach
                var="product"
                items="${productList}"
                varStatus="status"
              >
                <tr>
                  <td class="font-semibold">${status.count}</td>
                  <td>${product.name}</td>
                  <td>${product.price}</td>
                  <td>${product.limit_date}</td>
                  <td class="p-1.5">
                    <a
                      class="bg-blue-500 py-2 px-3 text-white border border-none rounded-md font-semibold"
                      href="/update?index=${status.index}"
                      ><spring:message code="page.list.update"
                    /></a>
                  </td>
                  <td class="p-1.5">
                    <a
                      class="bg-red-500 py-2 px-3 text-white border border-none rounded-md font-semibold"
                      href="/delete?index=${status.index}"
                      ><spring:message code="page.list.delete"
                    /></a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:if>
      </div>
      <div class="flex justify-end">
        <p
          class="w-52 bg-white text-center text-red-700 mb-5 px-2 py-1 rounded-l-lg"
        >
          ▲ <spring:message code="page.list.count1" />
          <span class="text-black"> ${productLen}</span>
          <spring:message code="page.list.count2" />
        </p>
      </div>
      <div class="flex justify-center">
        <a
          class="bg-red-500 text-white text-center w-25 px-3 py-2 font-semibold rounded-md cursor-pointer"
          href="/add"
          ><spring:message code="page.button.add"
        /></a>
      </div>
    </div>
    <script>
      const checkBtn = document.getElementById("checkBtn");

      const cookies = document.cookie.split("; ");
      let lang = "";
      for (let cookie of cookies) {
        const parts = cookie.split("=");
        if (parts[0] === "lang") {
          lang = parts[1];
          break;
        }
      }
      if (lang === "ko-KR") {
        checkBtn.checked = false;
      }
      if (lang === "en-US") {
        checkBtn.checked = true;
      }
      console.log(navigator.language);

      checkBtn.addEventListener("click", function () {
        if (this.checked) {
          document.cookie = "lang=en-US;path=/;";
        } else {
          document.cookie = "lang=ko-KR;path=/;";
        }
        location.reload();
      });
    </script>
  </body>
</html>
