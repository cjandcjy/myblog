<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<link rel="stylesheet" href="/myblog/static/user/editor/css/editormd.css"/>    
<script src="/myblog/static/user/editor/js/jquery.min.js"></script>
<script src="/myblog/static/user/editor/js/editormd.min.js"></script>
<script type="text/javascript" src="/myblog/static/form/jquery.form.js"></script>

<script type="text/javascript">
   function Submit(){
	   $("#aboutcontent")[0].value=$("#myhtmlcontent")[0].innerHTML;
	   if($("#myButton").html()=="修改关于页面"){
		   var options = {	
				   url:"${pageContext.request.contextPath}/manager/about/update",
				   success:function(msg){
   					$("#successTip").show();
   					$("#successTip p").html(msg);
   				}
		   };	   
		   $('#myform').ajaxForm(options);
	   }else{
		   var options = {	

				   success:function(msg){
   					$("#successTip").show();
   					$("#successTip p").html(msg);
   				}
		   };
		   
		   $('#myform').ajaxForm(options);
	   }
   }
</script>

<section class="content-header">
        <div id="failTip" class="alert alert-info" style="display:none">
	        <a href="#" class="close" data-dismiss="alert" >
		      &times;
		    </a>	 
            <h4>操作出现了问题</h4>
        </div>
    
        <div id="successTip" class="alert alert-info" style="display:none">
	        <a href="#" class="close" data-dismiss="alert" >
		      &times;
		    </a>	 
            <h4>编辑信息</h4>
            <p></p> 
        </div>
</section>

<section class="content">
	<form id="myform" action="${pageContext.request.contextPath}/manager/about/create" method="post">
	
	    <input type="hidden" name="id" value="${about.id}">
	    
	    <div id="layout">
            <textarea id="aboutcontent" name="content" style="display:none"></textarea>
			<div id="editormd">
			  <textarea id="markDown" name="markDown" style="display:none">${about.markDown}</textarea>
			</div>
		</div>
		<div class="row">
	            <div class="col-md-3"></div>
	            <div class="col-md-6">
	                <button type="submit" id="myButton" class="btn btn-block btn-default btn-lg"
	                        onclick="Submit()">创建关于页面</button>
	            </div>
	    </div>	
	</form>	
</section>



<!-- 初始化富文本编辑框 -->
 <script type="text/javascript">
            var testEditor;
            $(function () {
                testEditor = editormd("editormd", {
                    width: "94%",
                    height: 640,
                    syncScrolling: "single",
                    path: "/myblog/static/user/editor/lib/"
                });
                /*
                 testEditor = editormd({
                 id: "test-editormd",
                 width: "90%",
                 height: 640,
                 path: "/SBlog2/editor/lib/"
                 });*/

            });
</script>


<c:if test="${about!=null}">
  <script type="text/javascript">
	  $("#myButton").html("修改关于页面");	  
  </script>
</c:if>



