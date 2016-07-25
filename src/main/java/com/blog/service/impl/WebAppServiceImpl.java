package com.blog.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.dao.WebAppDao;
import com.blog.model.WebApp;
import com.blog.model.dto.WebAppDto;
import com.blog.service.WebAppService;

/*
 * created by cj
 * 2016/6/8
 */

@Service("webAppService")
public class WebAppServiceImpl implements WebAppService{
	
    @Autowired
    private WebAppDao webappdao;
	
	@Override
	public void saveWebApp(WebApp webApp) {
		try {
			webappdao.save(webApp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateWebApp(WebApp webApp) {
		 try {
			webappdao.update(webApp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public WebAppDto getWebDtoWebApp(Integer id) {
		WebAppDto dto = null;
		try {
		    dto = webappdao.getWebDto(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Integer getArticlesView() {
		int articalview = 0;
		try{
			articalview = webappdao.getArticlesView();
		}catch(Exception e){
			e.printStackTrace();
		}
		return articalview;
	}

	@Override
	public boolean webAppNotNull() {
		int count = 0;
		try {
			count = webappdao.count();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count==0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public List<WebApp> getWebAppDtos() {
		List<WebApp> list = null;
		try {
			list =  webappdao.getWebDtos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return list;
	}
 
}
