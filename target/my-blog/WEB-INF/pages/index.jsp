<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/my-blog/static/user/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="/my-blog/static/user/widget/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/my-blog/static/user/editor/css/editormd.css"/>
    <script src="/my-blog/static/user/widget/jquery/jquery.min.js"></script>
    <script src="/my-blog/static/user/widget/bootstrap/js/bootstrap.min.js"></script>
<title>${webAppDto.webTitle}</title>
</head>
<body>
  <%@include file="common/header.jsp"%>
  <div class="container-fluid">
   <div class="row">
      <div class="col-md-1"></div>
      <div class="col-md-10">
          <%@include file="user/article/articlelist.jsp" %>
      </div>
   </div>
  </div>
  <%@include file="common/footer.jsp" %>
</body>
</html>