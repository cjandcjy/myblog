<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>   
    <link rel="stylesheet" href="/myblog/static/user/css/article.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="article" items="${articles}">
  <div class="article well clearfix">
    <section class="hidden-xs">
      <div class="title-article">
        <h1><a href="article/${article.id}">${article.title}</a></h1>
      </div>
      <div class="tag-article">
         <span class="label"><a href="article/${article.id}" rel="author">${article.category.name}</a></span>
         <span class="label">${article.user.nickname}</span>
         <span class="label">${article.clicks}</span>
         <span class="label">${article.pubDate}</span>
      </div>
      <div class="content-article">
          <div class="alert alert-zan" align="center">${article.remark}</div> 
      </div>
      <a class="btn btn-danger pull-right read-more" href="article/${article.id}">阅读全文</a>
    </section>
  </div>
</c:forEach> 

  <!--如果没有search属性，则显示页码和翻页按钮  -->
  <c:if test="${search!='search' }">
     <div class="page-nav" align="center">
         <ul class="pagination">
         
           <li><a href="${pageContext.request.contextPath }/?pageIndex=${pager.firstPage }">首页</a></li>
           
           <c:choose>
            <c:when test="${pager.prePage!=-1}">
               <li><a href="${pageContext.request.contextPath }/?pageIndex=${pager.prePage }">上一页</a></li>
            </c:when>
            <c:otherwise>
               <li class="disabled"><a href="" >上一页</a></li>
            </c:otherwise>
           </c:choose>
           
           
           <c:if test="${pager.prePage!=-1 }">                                        
	           <c:forEach var="i" begin="1" end="${pager.prePage}">
	             <li><a href="${pageContext.request.contextPath }/?pageIndex=${i}">${i}</a></li> 
	           </c:forEach>          
           </c:if>
            
           <li class="active"><a href="${pageContext.request.contextPath }/?pageIndex=${pager.currentPage}">${pager.currentPage}</a></li>
           
           <c:if test="${pager.nextPage!=-1 }">   
             <c:forEach var="i" begin="${pager.nextPage}" end="${pager.lastPage}">                   
               <li><a href="${pageContext.request.contextPath }/?pageIndex=${i}">${i}</a></li>
             </c:forEach>           
           </c:if>
           
           
           <c:choose>
            <c:when test="${pager.nextPage!=-1 }">
              <li><a href="${pageContext.request.contextPath }/?pageIndex=${pager.nextPage }">下一页</a></li>   
            </c:when>
            <c:otherwise>
              <li class="disabled"><a href="">下一页</a></li>
            </c:otherwise>
           </c:choose>
           
           <li><a href="${pageContext.request.contextPath }?pageIndex=${pager.lastPage }">末页</a></li>
           
         </ul>
     </div>
  </c:if>
  
  <!-- 如果有search属性，说明此页面是用户使用了查询功能之后返回的结果 -->
  <c:if test="${search=='search'}">
     <div align="center">
       <h3>${info }</h3>
       <a href="${pageContext.request.contextPath}">回到首页</a>
     </div>
  </c:if> 