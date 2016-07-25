<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8"%>
<header>
   <div class="navbar navbar-inverse navbar-fixed-top">
     <div class="container">
      <div class="navbar-header">
        <!-- 自适应隐藏导航展开按钮 -->
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <!-- 导航条LOGO -->
        <a class="navbar-brand" href="${pageContext.request.contextPath}">${webAppDto.webName}</a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="active"><a href="${pageContext.request.contextPath}"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;首页</a></li>
          <li><a href="${pageContext.request.contextPath }/category/list"><span class="glyphicon glyphicon-th-list" aria-hidden="true">&nbsp;分类</a></li>
          <li><a href="${pageContext.request.contextPath }/archive"><span class="glyphicon glyphicon-inbox" aria-hidden="true"></span>&nbsp;归档</a></li>
          <li><a href="${pageContext.request.contextPath }/leave"><span class="glyphicon glyphicon-leaf" aria-hidden="true"></span>&nbsp;留言</a></li>
          <li><a href="${pageContext.request.contextPath }/about"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;关于</a></li>
        </ul>
        <form class="navbar-form navbar-right" role="search" action="${pageContext.request.contextPath}/search" method="post">
           <div class="form-group">
              <input type="text" class="form-control" placeholder="search" name="content"/>
           </div>
           <button type="submit" class="btn btn-success">GO</button>
        </form>
      </div> 
     </div>
   </div> 
</header>