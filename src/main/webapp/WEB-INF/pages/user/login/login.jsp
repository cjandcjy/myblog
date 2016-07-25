<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Sblog后台管理</title>
    <!-- Bootstrap core CSS -->
    <link href="/myblog/static/adminLTE/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<script type="text/javascript">
   function refresh(obj){
	   obj.src = "${pageContext.request.contextPath}/login/codeimg/?"+Math.random();
   }
   </script>
<div class="col-md-4"></div>
<div class="col-md-4" align="center">
    <c:if test="${error!=null}">
        <div class="alert alert-warning">
            <a href="#" class="close" data-dismiss="alert">
                &times;
            </a>
            <strong><h1>错误:</h1></strong>
            <h3>${error}</h3>
        </div>
    </c:if>

    <form class="form-signin" action="${pageContext.request.contextPath}/login" method="post">
        <h2 class="form-signin-heading">mylog后台管理</h2>
        <label for="username" class="sr-only">账户:</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="请输入用户名..." value="${username}">
        <label for="password" class="sr-only">密码:</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="请输入用户密码..." value="${password}">
        <label for="code" class="sr-only">验证码:</label>
        <input type="text" name="code" id="code" class="form-control" placeholder="请输入验证码..." />
        <img src="${pageContext.request.contextPath}/login/codeimg" onclick="refresh(this)"/>
        <div class="checkbox">
            <label>
                <input type="checkbox" name="remember" value="yes">记住我
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>

</div>
<!-- /container -->


</body>
</html>