<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>상품추가</title>

    <script src="https://cdn.tailwindcss.com"></script>
  </head>
  <body>
    <div class="w-1/2 m-auto mt-3 p-4 border border-4 border-black bg-red-700">
      <h2 class="text-white text-center font-bold text-2xl p-2 mb-3">
        <spring:message code="page.add.title" />
      </h2>
      <div class="flex flex-col items-center justify-center p-1">
        <form action="/add" method="post">
          <label for="name" class="w-full text-white text-lg my-1">
            <spring:message code="page.add.name" />:
          </label>
          <input
            class="w-full text-lg rounded-md my-1 py-1"
            id="name"
            name="pName"
            type="text"
            value=""
            required
          />
          <label for="price" class="w-full text-white text-lg my-1">
            <spring:message code="page.add.price" />:</label
          >
          <input
            class="w-full text-lg rounded-md my-1 py-1"
            id="price"
            name="pPrice"
            type="number"
            value=""
            required
          />
          <label for="expDate" class="w-full text-white text-lg">
            <spring:message code="page.add.limitDate" />:</label
          >
          <input
            class="w-full text-lg rounded-md my-1 py-1"
            id="expDate"
            name="pExpDate"
            type="date"
            value=""
            required
          />
          <div class="flex justify-center items-center">
            <button
              type="submit"
              class="mt-3 bg-black text-white text-center w-25 px-3 py-2 font-semibold rounded-md cursor-pointer mr-1"
            >
              <spring:message code="page.add.button.complete" />
            </button>
            <button
              type="button"
              class="inline ml-1 mt-3 bg-white text-black text-center w-25 px-3 py-2 font-semibold rounded-md cursor-pointer ml-1"
              onclick="history.back()"
            >
              <spring:message code="page.button.back" />
            </button>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
