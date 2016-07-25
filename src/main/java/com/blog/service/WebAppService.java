package com.blog.service;

import java.util.List;

import com.blog.model.WebApp;
import com.blog.model.dto.WebAppDto;

public interface WebAppService {
	
    public void saveWebApp(WebApp webApp);
    
    public void updateWebApp(WebApp webApp);
    
    //根据给定的id查询某一个用户的数据（主页姓名，主页标题，每页展示的文章数，点击量）
    public WebAppDto getWebDtoWebApp(Integer id);
    
    public Integer getArticlesView();
    
    public boolean webAppNotNull();
    
    //获取所有用户的数据（主页姓名，主页标题，每页展示的文章数等）
    public List<WebApp> getWebAppDtos();
}
