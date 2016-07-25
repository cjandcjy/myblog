<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>   
    <link rel="stylesheet" href="/myblog/static/user/css/article.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${error!=null}">
   <div class="alert alert-warning">
     <a href="#" class="close" data-dismiss="alert">
        &times;
     </a>
     <strong><h1>错误:</h1></strong>
     <h3>${error}</h3>
     <a href="${request.getRequestPath}">回到首页</a>
   </div>
</c:if>

<c:if test="${error==null}">
    <ol class="breadcrumb">
      <li><a href="${request.getRequestPath}">首页</a></li>
      <li><a href="category/${article.category.id}">${article.category.name}</a></li>
      <li>${article.title}</li>
    </ol>
    <article class="well">
       <session class="article-content clearfix article-page">
          ${article.content }
       </session>
       <div align="center">
         <session class="article-copyright">
            <p>
            
 <!-- href中的路径如果不是以“/”开头的话，指的是相对路径，即相对于当前访问的路径来搜索 -->
              上一篇：<a href="${preArticle.id}">${preArticle.title}</a>
               下一篇:<a href="${nextArticle.id}">${nextArticle.title}</a>      
            </p>
            <p>
            转载注明：<a href="/about">健酱</a>&gt;&gt;<a href="${article.id}">${article.title}</a>
            </p>
         </session>
       </div>
    </article>
    
        
    <section>
    <div class="ds-thread" data-thread-key="${article.id}" data-title="${article.title}"
         data-url="/article/${article.id}"></div>
    <!-- 多说评论框 end -->
    <!-- 多说公共JS代码 start (一个网页只需插入一次) -->
    <script type="text/javascript">
        var duoshuoQuery = {short_name: "xingfly"};
        (function () {
            var ds = document.createElement('script');
            ds.type = 'text/javascript';
            ds.async = true;
            ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
            ds.charset = 'UTF-8';
            (document.getElementsByTagName('head')[0]
            || document.getElementsByTagName('body')[0]).appendChild(ds);
        })();
    </script>
    </section>

</c:if>