package com.blog.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.constant.AttributeConstant;
import com.blog.model.dto.UserDto;
import com.blog.model.dto.WebAppDto;
import com.blog.service.WebAppService;

@Controller
public class ManagerController {
	
	@Autowired
	WebAppService webappservice;
	
    @RequestMapping(value="/manager")
    public String manage(ModelMap map,HttpSession session){
          	UserDto userDto = (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER);
       	    WebAppDto webAppDto = webappservice.getWebDtoWebApp(webappservice.getWebAppDtos().get(0).getId());
          	webAppDto.setArticleViews(webappservice.getArticlesView());
       		map.addAttribute(AttributeConstant.MAIN_PAGE, "main/main.jsp");
       		map.addAttribute(AttributeConstant.WEB_APP_DTO, webAppDto);
       		map.addAttribute(AttributeConstant.CURRENT_USER, userDto);
       		return "admin/index";
    }
}
