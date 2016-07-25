<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ol class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li class="active">归档</li>
</ol>
<div>
  <div class="category">
     <ul class="list-group">
       <c:forEach var="article" items="${articles}">
         <li class="list-group-item">
           <span class="badge">${article.pubDate}</span>
           <a href="${pageContext.request.contextPath}/article/${article.id}">${article.title}</a>
         </li>
       </c:forEach>
     </ul>
  </div>
</div>