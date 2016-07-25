<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<style>
    .tableCenter th, .tableCenter td {
        text-align: center;
        height: 38px;
    }
</style>

<script type="text/javascript">
  function delcfm(articleid){
	  if(!confirm("确认删除吗？")){
		  windows.event.returnValue="false";
	  }else{
		  $.ajax({
			  url:"${pageContext.request.contextPath }/manager/article/delete",
			  type:"POST",
			  data:{"articleid":articleid},
		      success:function(msg){
		    	  $("#"+articleid).remove();
		      },
		      error:function(){
		    	  alert("删除操作失败");
		      }
		  });
	  }
  }
  
</script>



  <section class="content" >
     <div class="row">
        <div class="col-xs-12">
           <div class="box">
           
              <div class="box-header">
                 <div class="col-md-2 clearfix">
                     <h5 class="box-title">文章信息</h5>
                 </div>
                  <div class="col-md-4 col-md-offset-6 clearfix">
                     <form action="${pageContext.request.contextPath}/manager/article/search" method="post">
                     <input type="text" name="title" placeholder="文章搜索...">
                     <button type="submit" class="btn btn-primary">GO</button>
                    </form>
                  </div>
              </div>
              
              <div class="box-body no-padding table-responsive">
                 <table class="table table-hover tableCenter">
                    <tbody>
                      <tr>
                        <th>ID</th>
                        <th>标题</th>
                        <th>分类</th>
                        <th>作者</th>
                        <th>日期</th>
                        <th>浏览</th>
                        <th>编辑</th>
                        <th>删除</th>
                      </tr>
                      <c:forEach var="article" items="${articles}">
                        <tr id="${article.id}">
                          <td>${article.id}</td>
                          <td>${article.title}</td>
                          <td>${article.category.name}</td>
                          <td>${article.user.nickname}</td>
                          <td>${article.pubDate}</td>
                          <td><span class="badge bg-red">${article.clicks}</span></td>
                          <td>
                             <button class="btn btn-block btn-info btn-sm"><a href="${pageContext.request.contextPath }/manager/article/update/${article.id}">编辑</a></button>
                          </td>
                          <td>
                              <button class="btn btn-block btn-danger btn-sm" onclick="delcfm(${article.id})">删除</button>
                          </td>
                        </tr> 
                      </c:forEach>
                    </tbody>
                 </table>
              </div>
              
              <div class="box-footer">
                <div class="pull-right">
                  <ul class="pagination">
                       <li><a href="${pageContext.request.contextPath }/manager/article/?pageIndex=${pager.firstPage }">首页</a></li>
           
				       <c:choose>
				        <c:when test="${pager.prePage!=-1}">
				           <li><a href="${pageContext.request.contextPath }/manager/article/?pageIndex=${pager.prePage }">上一页</a></li>
				        </c:when>
				        <c:otherwise>
				           <li class="disabled"><a href="" >上一页</a></li>
				        </c:otherwise>
				       </c:choose>
           			           
			           
			           <c:if test="${pager.prePage!=-1 }">                                        
				           <c:forEach var="i" begin="1" end="${pager.prePage}">
				             <li><a href="${pageContext.request.contextPath }/manager/article/?pageIndex=${i}">${i}</a></li> 
				           </c:forEach>          
			           </c:if>
			           
			           <li class="active"><a href="${pageContext.request.contextPath }/manager/article/?pageIndex=${pager.currentPage}">${pager.currentPage}</a></li>
			           
					   <c:if test="${pager.nextPage!=-1 }">   
				             <c:forEach var="i" begin="${pager.nextPage}" end="${pager.lastPage}">                   
				               <li><a href="${pageContext.request.contextPath }/manager/article/?pageIndex=${i}">${i}</a></li>
				             </c:forEach>           
				       </c:if> 
			           
			           <c:if test="${pager.nextPage!=-1 }">                      
			             <li><a href="${pageContext.request.contextPath }/manager/article/?pageIndex=${pager.nextPage}">${pager.nextPage}</a></li>           
			           </c:if>
			           			           			           
			           <c:choose>
			            <c:when test="${pager.nextPage!=-1 }">
			              <li><a href="${pageContext.request.contextPath }/manager/article/?pageIndex=${pager.nextPage }">下一页</a></li>   
			            </c:when>
			            <c:otherwise>
			              <li class="disabled"><a href="">下一页</a></li>
			            </c:otherwise>
			           </c:choose>
           
                        <li><a href="${pageContext.request.contextPath }/manager/article/?pageIndex=${pager.lastPage }">末页</a></li>
                  </ul>
                  </div>
              </div>
           </div>
        </div>
     </div>
  </section>
  


