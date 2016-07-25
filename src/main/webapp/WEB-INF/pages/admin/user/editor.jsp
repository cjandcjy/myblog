<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<script type="text/javascript" src="/myblog/static/form/jquery.form.js"></script>

<style>
    .tableCenter th, .tableCenter td {
        text-align: center;
        height: 38px;
    }
</style>

<script type="text/javascript">
    function delcfm(userid) {
        if (!confirm("确认要删除？")) {
            window.event.returnValue = false;
        }else{
        	$.ajax({
        		url:"${pageContext.request.contextPath }/manager/user/delete",
        		data:{"userId":userid},
        		type:"POST",
        		success:function(msg){
        			$("#"+userid).remove();
        			$("#successTip").show();
        			$("#successTip p").html(msg);
        		},
        		error:function(){
        			$("#failTip").show();
        		}
        	});
        }
    }
    
    function update(userid){
    	var $user = $("#"+userid).find("td");
    	$("#userid").val($user[0].innerHTML)
        $("#myusername").val($user[1].innerHTML);
        $("#userpassword").val($user[2].innerHTML);
        $("#usernickname").val($user[3].innerHTML);
        $("#useremail").val($user[4].innerHTML);
        $("#userform").action = "${pageContext.request.contextPath }/manager/user/update";
        $("#mybutton").html("更新用户");       
    }
    
</script>

<script type="text/javascript">
  $(function(){
	  $("#mybutton").click(function(){
		 if($("#mybutton").html()=='创建用户'){
			 alert("创建用户");
			 var options = {
					 url:"${pageContext.request.contextPath }/manager/user/create",
					 type:"POST",
					 success:function(msg){
		        			$("#successTip").show();
		        			$("#successTip p").html(msg);
					 }
			 };
			 $("#userform").ajaxForm(options);
		 }else{
			 alert("更新用户");
			 var options = {
					 url:"${pageContext.request.contextPath }/manager/user/update",
					 type:"POST",
					 success:function(msg){
		        			$("#successTip").show();
		        			$("#successTip p").html(msg);
					 }
			 };
			 $("#userform").ajaxForm(options);
		 }
	  });
  });
</script>

<section class="content-header">
   
        <div id="failTip" class="alert alert-info" style="display:none">
	        <a href="#" class="close" data-dismiss="alert" >
		      &times;
		    </a>	 
            <h4>添加用户失败</h4>
        </div>
    
        <div id="successTip" class="alert alert-info" style="display:none">
	        <a href="#" class="close" data-dismiss="alert" >
		      &times;
		    </a>	 
            <h4>操作信息</h4>
            <p></p> 
        </div>
    
</section>

<section class="content">
    <form id="userform" action="${pageContext.request.contextPath }/manager/user/create" method="post">
        <!-- 隐藏域，用户id -->
        <input type="hidden" id="userid" name="id">
        
        <div class="row">
            <div class="box box-default">
                <div class="box-header with-border">
                    <h6>创建用户:</h6>
                    <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    </div>
                </div>
                <div id="collapseExample" class="box-body collapse" style="display:block;">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="myusername">用户名称:</label>
                                <input type="text" name="username" class="form-control" id="myusername"
                                       placeholder="请输入用户名称..."/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="userpassword">用户密码:</label>
                                <input type="password" name="password" class="form-control" id="userpassword"
                                       placeholder="请输入用户密码..."/>
                                <textarea style="display: none" id="userid" name="id"></textarea>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="usernickname">用户昵称:</label>
                                <input type="text" name="nickname" class="form-control" id="usernickname"
                                       placeholder="请输入用户昵称..."/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="useremail">用户邮箱:</label>
                                <input type="text" name="email" class="form-control" id="useremail"
                                       placeholder="请输入用户邮箱..."/>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <label for="mybutton"></label>
                            <button type="submit" id="mybutton" class="btn btn-block btn-default btn-flat"
                                   >创建用户 </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </form>
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">用户信息</h3>
                </div>

                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover tableCenter">
                        <tbody>
                        <tr>
                            <th>ID</th>
                            <th>用户名称</th>
                            <th>用户密码</th>
                            <th>用户昵称</th>
                            <th>用户邮箱</th>
                            <th>编辑</th>
                            <th>删除</th>
                        </tr>
                       <c:forEach var="user" items="${users}">
	                        <tr id="${user.id}">
	                            <td width="10%">${user.id}</td>
	                            <td>${user.username}</td>
	                            <td>${user.password}</td>
	                            <td>${user.nickname}</td>
	                            <td>${user.email}</td>
	                            <td width="10%">
	                                <button class="btn btn-block btn-info" onclick="update(${user.id})">编辑</button>
	                            </td>
	                            <td width="10%">
	                                <button class="btn btn-block btn-danger" onclick="delcfm(${user.id})">删除</button>
	                            </td>
	                        </tr>
                       </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>