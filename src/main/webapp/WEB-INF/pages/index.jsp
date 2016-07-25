<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/myblog/static/user/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="/myblog/static/user/widget/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/myblog/static/user/editor/css/editormd.css"/>
    <script src="/myblog/static/user/widget/jquery/jquery.min.js"></script>
    <script src="/myblog/static/user/widget/bootstrap/js/bootstrap.min.js"></script>
    
<title>${webAppDto.webTitle}</title>
</head>
<body>
  <div>
     <%@include file="common/header.jsp"%>
  </div>
  <div class="container-fluid">    
  <div class="row">
      <div class="col-md-1"></div>
      <div class="col-md-10">
          <jsp:include page="${mainPage }"></jsp:include>
      </div>
  </div>
  </div>
  <%@include file="common/footer.jsp" %>
</body>
</html>