<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>   
    <link rel="stylesheet" href="/myblog/static/user/css/article.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${error!=null}">
  <div class="alert alert-warning">
    <a href="#" class="close" data-dismiss="alert">&times;</a>
    <strong><h1>错误:</h1></strong>
    <h3>${error}</h3>
    <a href="/">返回首页</a>
  </div>
</c:if>  
  
<c:if test="${error==null}">
   <ol class="breadcrumb">
     <li><a href="${request.getContextPath}">首页</a></li>
     <li><a href="category/list">分类</a></li>
     <li class="active">${category.name}</li>
   </ol>
   
   <c:if test="${articles==null}">
     <div class="alert alert-warning">
        <a href="#" class="close" data-dismiss="alert">
            &times;
        </a>
        <strong><h1>提示:</h1></strong>

        <h3>此分类下没有文章。</h3>
     </div>
   </c:if>
   
   <c:if test="${articles!=null}">
       <table class="table table-striped table-hover  table-condensed">
           <tbody>
             <c:forEach var="article" items="${articles}">
                <tr>
                  <td width="50%" align="right">
                    ${article.pubDate}:
                  </td>
                  <td width="50%" align="left">
                    <a href="${pageContext.request.contextPath}/article/${article.id}">${article.title}</a>
                  </td>
                </tr>
             </c:forEach>
           </tbody>
       </table>      
   </c:if>
</c:if>