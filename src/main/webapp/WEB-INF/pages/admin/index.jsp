<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<%
  String ctx = request.getContextPath();
%>


<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="/myblog/static/user/widget/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/myblog/static/user/widget/bootstrap/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css">
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
    rel="stylesheet" type="text/css">
    <link href="/myblog/static/adminLTE/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css">
    
<style>


</style>
  </head>
  
  <body>
  
    <div class="navbar navbar-default navbar-static-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand"><span>MyBlog</span></a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-ex-collapse">
          <ul class="nav navbar-nav navbar-right">
            <li>
              <a href="#">Admin</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-2">
            <ul class="nav nav-pills nav-stacked">
              <li class="active">
                <a href="${pageContext.request.contextPath}/manager">Home</a>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                aria-expanded="false"><font face="FontAwesome"><span style="line-height: 14px;">&nbsp;账户管理</span></font><i class="fa fa-caret-down"></i></a>
                <ul class="dropdown-menu" role="menu">
                  <li>
                    <a href="${pageContext.request.contextPath}/manager/user">编辑账户</a>
                  </li>                 
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                aria-expanded="false">网站管理<i class="fa fa-caret-down"></i></a>
                <ul class="dropdown-menu" role="menu">
                  <li>
                    <a href="#">基础设置</a>
                  </li>                  
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                aria-expanded="false"><font face="FontAwesome"><span style="line-height: 14px;">关于管理</span></font><i class="fa fa-caret-down"></i></a>
                <ul class="dropdown-menu" role="menu">
                  <li>
                    <a href="${pageContext.request.contextPath}/manager/about">修改关于</a>
                  </li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                aria-expanded="false">文章管理&nbsp;<i class="fa fa-caret-down"></i></a>
                <ul class="dropdown-menu" role="menu">
                  <li>
                    <a href="${pageContext.request.contextPath}/manager/article/create">创建文章</a>
                  </li>
                  <li>
                    <a href="${pageContext.request.contextPath}/manager/article">编辑文章</a>
                  </li>                
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                aria-expanded="false">分类管理&nbsp;<i class="fa fa-caret-down"></i></a>
                <ul class="dropdown-menu" role="menu">
                  <li>
                    <a href="${pageContext.request.contextPath}/manager/category">编辑分类</a>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
          <div class="col-md-10">
             <jsp:include page="${mainPage}"></jsp:include>
          </div>
        </div>
      </div>
    </div>
    <footer id="footer" class="section section-primary main-footer">
      <div class="container-fluid">
        <div class="row">
          <div class="col-sm-6">
            <h1>Footer header</h1>
            <p>Lorem ipsum dolor sit amet, consectetur adipisici elit,
              <br>sed eiusmod tempor incidunt ut labore et dolore magna aliqua.
              <br>Ut enim ad minim veniam, quis nostrud</p>
          </div>
          <div class="col-sm-6">
            <p class="text-info text-right">
              <br>
              <br>
            </p>
            <div class="row">
              <div class="col-md-12 hidden-lg hidden-md hidden-sm text-left">
                <a href="#"><i class="fa fa-3x fa-fw fa-instagram text-inverse"></i></a>
                <a href="#"><i class="fa fa-3x fa-fw fa-twitter text-inverse"></i></a>
                <a href="#"><i class="fa fa-3x fa-fw fa-facebook text-inverse"></i></a>
                <a href="#"><i class="fa fa-3x fa-fw fa-github text-inverse"></i></a>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12 hidden-xs text-right">
                <a href="#"><i class="fa fa-3x fa-fw fa-instagram text-inverse"></i></a>
                <a href="#"><i class="fa fa-3x fa-fw fa-twitter text-inverse"></i></a>
                <a href="#"><i class="fa fa-3x fa-fw fa-facebook text-inverse"></i></a>
                <a href="#"><i class="fa fa-3x fa-fw fa-github text-inverse"></i></a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
    
    <!-- 模态框（Modal） -->
   <div id="mymodal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	  <div class="modal-dialog modal-sm">
	    <div class="modal-content">
	      <div class="modal-body">         
	                  提交成功，3s后跳转
	      </div>
	     <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal" onclick="refresh()">关闭
            </button>           
         </div>
	    </div>
	    
	  </div>
  </div>
   
   <script type="text/javascript">
        var i=3;
	    var intervalid;
        function refresh(){
        	$("#successText").show();
        	intervalid=setInterval("func()", 1000);   
        }
        function func(){
    		if(i==0){
    			$("#countdown").html(i);
    			window.location.href="${pageContext.request.contextPath}/manager/article";
    			clearInterval(intervalid);
    		}else{
    			$("#countdown").html(i);
    			i--;
    		}
    	}
   </script>
  </body>

</html>