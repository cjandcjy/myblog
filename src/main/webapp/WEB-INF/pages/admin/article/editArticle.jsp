<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

    <script type="text/javascript" src="/myblog/static/bootstrap-wysiwyg/jquery.hotkeys.js"></script>    
    <script type="text/javascript" src="/myblog/static/bootstrap-wysiwyg/bootstrap-wysiwyg.js"></script>
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <link href="/myblog/static/bootstrap-wysiwyg/index.css" rel="stylesheet" type="text/css">
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css" rel="stylesheet">
	<script type="text/javascript" src="/myblog/static/bootstrap-wysiwyg/prettify.js"></script>
    <script type="text/javascript" src="/myblog/static/form/jquery.form.js"></script>

<script type="text/javascript">
   $(function(){	  
	  $("#articletitle").val('${article.title}');
	  $("#sel").val('${article.category.id}');
	  $("#articleremark").val('${$article.remark}');
	 <!-- $("#editor").val('${article.content}');-->
	  $("#editor").html('${article.content}');
   });
</script>

<div class="container-fluid">
<div class="hero-unit">
  <div class="row">
     <div class="col-md-4">
        <h2></h2><small><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>文章编辑</small></h2>
     </div>
     <div class="col-md-8">
	   <div class="pull-right">
	    <div class="fb-like" data-href="http://facebook.com/mindmupapp" data-send="false" data-layout="button_count" data-width="100" data-show-faces="false">
	    </div><br>
	    <a href="https://twitter.com/mindmup" class="twitter-follow-button" data-show-count="true" data-show-screen-name="true" data-lang="en">Follow @mindmup</a> 
	   </div>
     </div>
  </div>
  
  <div id="failText" class="alert alert-info" style="display:none">
	   <a href="#" class="close" data-dismiss="alert">
	      &times;
	   </a>
   </div>
   
   <div id="successText" class="alert alert-success" style="display:none">
	   <a href="#" class="close" data-dismiss="alert" >
	      &times;
	   </a>	  
	   <p id="countdown" ></p>秒后跳转，请等待... 
   </div>
   
  <form id="submitform" action="${pageContext.request.contextPath}/manager/article/create" method="post">

      <input name="id" type="hidden" value="${article.id}">
      
      <input name="userId" type="hidden" value="${user.id}">
     
      <div class="row" style="display:block;">                 
        <div class="col-md-6">
            <div class="form-group">
                <label for="articletitle">文章标题:</label>
                <input type="text" name="title" class="form-control" id="articletitle"
                       placeholder="请输入文章标题..."/>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label>文章分类:</label>
                <select name="categoryId" id="sel"
                        class="form-control select2 select2-hidden-accessible"
                        style="width: 100%;" tabindex="-1" aria-hidden="true">
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}" id="category${category.id}">
                           ${category.name}
                        </option>
                   </c:forEach>
                </select>
            </div>
       </div>
     </div>
     <div class="row" style="display:block;">
        <div class="col-md-12">
           <div class="form-group">
                <label for="articleremark">文章简介:</label>
                <input type="text" name="remark" class="form-control" id="articleremark"
                       placeholder="请输入文章简介..."/>
            </div>
        </div>
     </div>
                   
  

 <div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
      <div class="btn-group">
        <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font"><i class="icon-font"></i><b class="caret"></b></a>
          <ul class="dropdown-menu">
          </ul>
      </div>
      
      <div class="btn-group">
        <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font Size"><i class="icon-text-height"></i>&nbsp;<b class="caret"></b></a>
          <ul class="dropdown-menu">
          <li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
          <li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
          <li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
          </ul>
      </div>
      <div class="btn-group">
        <a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i class="icon-bold"></i></a>
        <a class="btn" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a>
        <a class="btn" data-edit="strikethrough" title="Strikethrough"><i class="icon-strikethrough"></i></a>
        <a class="btn" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i class="icon-underline"></i></a>
      </div>
      <div class="btn-group">
        <a class="btn" data-edit="insertunorderedlist" title="Bullet list"><i class="icon-list-ul"></i></a>
        <a class="btn" data-edit="insertorderedlist" title="Number list"><i class="icon-list-ol"></i></a>
        <a class="btn" data-edit="outdent" title="Reduce indent (Shift+Tab)"><i class="icon-indent-left"></i></a>
        <a class="btn" data-edit="indent" title="Indent (Tab)"><i class="icon-indent-right"></i></a>
      </div>
      <div class="btn-group">
        <a class="btn" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><i class="icon-align-left"></i></a>
        <a class="btn" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i class="icon-align-center"></i></a>
        <a class="btn" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i class="icon-align-right"></i></a>
        <a class="btn" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i class="icon-align-justify"></i></a>
      </div>
      <div class="btn-group">
		  <a class="btn dropdown-toggle" data-toggle="dropdown" title="Hyperlink"><i class="icon-link"></i></a>
		    <div class="dropdown-menu input-append">
			    <input class="span2" placeholder="URL" type="text" data-edit="createLink"/>
			    <button class="btn" type="button">Add</button>
        </div>
        <a class="btn" data-edit="unlink" title="Remove Hyperlink"><i class="icon-cut"></i></a>
      </div>
      
      <div class="btn-group">
        <a class="btn" title="Insert picture (or just drag & drop)" id="pictureBtn"><i class="icon-picture"></i></a>
        <input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" />
      </div>
      <div class="btn-group">
        <a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="icon-undo"></i></a>
        <a class="btn" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>
      </div>
      <input type="text" data-edit="inserttext" id="voiceBtn" x-webkit-speech="">
 </div>
     <div id="editor" contenteditable="true">    
               输入内容&hellip;     
     </div>
     
     <textarea name="markDown" id="markDown" style="display:none">
     </textarea>
     <textarea name="content" id="content" style="display:none">
     </textarea>
     
     <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <button type="submit" id="myButton" class="btn btn-block btn-default btn-lg">发表文章</button>
            </div>
     </div>
   </form>
 </div>
</div>

<script type="text/javascript">
   $(document).ready(function(){
	   
	   $("#myButton").click(function(){
		   $("#markDown").val($("#editor").html());
		   
		   $("#content").val($("#editor").html());
		   			   
		   var options = {	

				   success:showResponse
		   };
		   function showResponse(data){		
			      <!--弹出模态框-->
				   $("#mymodal").modal('show');		   			   
		   }
		   $('#submitform').ajaxForm(options);
	   });	   
	   
   });
</script>

<script type="text/javascript">         
  $(function(){
    function initToolbarBootstrapBindings() {
      var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier', 
            'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
            'Times New Roman', 'Verdana'],
            fontTarget = $('[title=Font]').siblings('.dropdown-menu');
      $.each(fonts, function (idx, fontName) {
          fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
      });
      $('a[title]').tooltip({container:'body'});
    	$('.dropdown-menu input').click(function() {return false;})
		    .change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
        .keydown('esc', function () {this.value='';$(this).change();});

      $('[data-role=magic-overlay]').each(function () { 
        var overlay = $(this), target = $(overlay.data('target')); 
        overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
      });
      if ("onwebkitspeechchange"  in document.createElement("input")) {
        var editorOffset = $('#editor').offset();
        $('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
      } else {
        $('#voiceBtn').hide();
      }
	};
	function showErrorAlert (reason, detail) {
		var msg='';
		if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
		else {
			console.log("error uploading file", reason, detail);
		}
		$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
		 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
	};
    initToolbarBootstrapBindings();  
	$('#editor').wysiwyg({ fileUploadError: showErrorAlert} );
    window.prettyPrint && prettyPrint();
  });
</script>

