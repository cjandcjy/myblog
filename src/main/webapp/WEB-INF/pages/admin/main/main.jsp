<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    <div class="section">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <h3>${webAppDto.webName }</h3>
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-5">
           <div>网站信息详情</div>
           <div>
            <table class="table">
              <tbody>
                <tr>
                  <td>网站名称</td>
                  <td>${webAppDto.webName}</td>
                </tr>
                <tr>
                  <td>网站标题</td>
                  <td>${webAppDto.webTitle }</td>
                </tr>
                <tr>
                  <td>用户文章显示数量</td>
                  <td>${webAppDto.userPageArticleSize }</td>
                </tr>
                <tr>
                  <td>管理员文章显示数量</td>
                  <td>${webAppDto.adminPageArticleSize }</td>
                </tr>
                <tr>
                  <td>网站文章浏览总量</td>
                  <td>${webAppDto.articleViews}</td>
                </tr>
              </tbody>
            </table>
           </div>
          </div>
          <div class="col-md-2"></div>
          <div class="col-md-5">
           <div>网站信息编辑</div>
           <div>
            <form class="form-horizontal" role="form">
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputEmail3" class="control-label">网站名称</label>
                </div>
                <div class="col-sm-10">
                  <input type="email" class="form-control" id="inputEmail3" placeholder="网站名称（可空）">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">网站标题</label>
                </div>
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="inputPassword3" placeholder="网站标题（可空）">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">页面显示</label>
                </div>
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="inputPassword3" placeholder="首页文章显示数（可空）">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">页面显示</label>
                </div>
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="inputPassword3" placeholder="管理文章显示数（可空）">
                </div>
              </div>
                           
              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" class="btn btn-default">提交更新</button>
                </div>
              </div>
            </form>
           </div>
          </div>
        </div>
      </div>
    </div>

