<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ol class="breadcrumb">
    <li><a href="${pageContext.request.contextPath}">首页</a></li>
    <li class="active">关于</li>
</ol>

<article class="well">
    <section class="article-content clearfix article-page">
        <c:if test="${about!=null}">
          ${about.content}
        </c:if>
    </section>
</article>
