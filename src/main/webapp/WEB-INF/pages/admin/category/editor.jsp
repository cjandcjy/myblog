<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript" src="/myblog/static/form/jquery.form.js"></script>

<style>
    .tableCenter th, .tableCenter td {
        text-align: center;
        height: 38px;
    }
</style>

<script type="text/javascript">
    function delcfm(categoryid) {
        if (!confirm("确认要删除？")) {
            window.event.returnValue = false;
        }else{
        	$.ajax({
        		url:"${pageContext.request.contextPath }/manager/category/delete",
        		type:"POST",
        		data:{"categoryid":categoryid},
        		success:function(msg){
        			$("#successTip").show();
        			$("#successTip p").html(msg);
        			$("#"+categoryid).remove();
        		}
        	});
        }
    }

    
    function update(categoryid){   	
    	/*获得jquery对象 $category*/
    	var $category = $("#"+categoryid).find("td");
    	
    	/*$category[0]获得的是DOM元素*/
    	$("#categoryid").val($category[0].innerHTML);
    	$("#categoryname").val($category[1].innerHTML);
    	$("#mysubmit").html("修改分类"); 
    }
    
    $(function(){
    	$("#mysubmit").click(function(){
    		if($("#mysubmit").html()=="修改分类"){
    			alert("修改分类");
    			var options={
    					url:"${pageContext.request.contextPath }/manager/category/update",
    				success:function(msg){
    					$("#successTip").show();
    					$("#successTip p").html(msg);
    				}
    			};
    			$("#categoryform").ajaxForm(options);
    		}else{
    			alert("创建分类");
    			var options={ 
    					
        				success:function(msg){
        					$("#successTip").show();
        					$("#successTip p").html(msg);
        				}
        			};
        			$("#categoryform").ajaxForm(options);
    		}
    	});
    });
</script>



<section class="content-header">
        <div id="failTip" class="alert alert-info" style="display:none">
	        <a href="#" class="close" data-dismiss="alert" >
		      &times;
		    </a>	 
            <h4>编辑分类出现了问题</h4>
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
    <form id="categoryform"  action="${pageContext.request.contextPath }/manager/category/create" method="post">
    
    <!-- 隐藏域 -->
    <input type="hidden" name="categoryid" id="categoryid">
    
        <div class="row">
            <div class="box box-default">
                <div class="box-header with-border">
                    <h6>创建分类:</h6>

                    <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    </div>
                </div>
                <div class="box-body" style="display:block;">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="categoryname">分类名称:</label>
                                <input type="text" name="categoryName" class="form-control" id="categoryname"
                                       placeholder="请输入分类名称..."/>
                                <textarea style="display: none" id="categoryid" name="categoryId"></textarea>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="createbutton">😊🌹</label>
                            <button id="mysubmit" type="submit" class="btn btn-block btn-default btn-flat"
                                    >创建分类</button>
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
                    <h3 class="box-title">分类信息</h3>
                </div>

                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover tableCenter">
                        <tbody>
                        <tr>
                            <th>ID</th>
                            <th>名称</th>
                            <th>数量</th>
                            <th>编辑</th>
                            <th>删除</th>
                        </tr>
                         <c:forEach var="category" items="${categories}">
                            <tr id="${category.id }">
                                <td width="10%">${category.id }</td>
                                <td>${category.name}</td>
                                <td><span class="badge bg-red">${category.count }</span></td>
                                <td width="10%">
                                    <button class="btn btn-block btn-info" onclick="update(${category.id})">编辑</button>
                                </td>
                                <td width="10%">
                                    <button class="btn btn-block btn-danger" onclick="delcfm(${category.id})">删除</button>
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