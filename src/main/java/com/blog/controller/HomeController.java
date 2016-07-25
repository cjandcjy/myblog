package com.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.constant.AttributeConstant;
import com.blog.model.WebApp;
import com.blog.service.ArticleService;
import com.blog.service.WebAppService;
import com.blog.util.Pager;
import com.blog.util.StringUtil;


@Controller
@RequestMapping("/")
public class HomeController {

  @Autowired
  private ArticleService articleService;
  
  @Autowired
  private WebAppService webAppService;
  
  @RequestMapping(method=RequestMethod.GET)
  public String home(@RequestParam(value="pageIndex",defaultValue="1")Integer pageIndex , ModelMap map,HttpServletRequest request){
	  String link = "index";
	  if(webAppService.getWebAppDtos().size()==0){
		  link = "redirect:/init";
		  return link;
	  }else{
		  Pager pager = new Pager(pageIndex, webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()).getUserPageArticleSize(), articleService.count());
		  map.addAttribute(AttributeConstant.MAIN_PAGE, "user/article/articlelist.jsp");
		  map.addAttribute(AttributeConstant.PAGER,pager);
		  map.addAttribute(AttributeConstant.ARTICLES, articleService.getPageArticles(pager));
		  map.addAttribute(AttributeConstant.WEB_APP_DTO,webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));
	  }
	  return link;
  }
  
  @RequestMapping(value="init" , method = RequestMethod.GET)
  public String init(ModelMap map){
	  return "admin/init/init";
  }
  @RequestMapping(value="init",method = RequestMethod.POST)
  public String initAction(ModelMap map,WebApp webApp){
	  if(StringUtil.isNotEmpty(webApp.getWebName())&&StringUtil.isNotEmpty(webApp.getWebTitle())){
		  if(StringUtil.isNotEmpty(webApp.getAdminPageArticleSize().toString())&&StringUtil.isNotEmpty(webApp.getUserPageArticleSize().toString())){
			  webAppService.saveWebApp(webApp);
			  return "redirect:/";
		  }
	  }
	  return "redirect:/init";
  }

}