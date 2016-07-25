<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>   
    <link rel="stylesheet" href="/myblog/static/user/css/article.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="category">
  <ul class="list-group">
     <c:forEach var="category" items="${categories}">
       <li class="list-group-item">
          <span class="badge">${category.count}</span>
          <a href="${category.id}">${category.name}</a>
       </li>
     </c:forEach>
  </ul>
</div>