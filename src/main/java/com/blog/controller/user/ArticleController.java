package com.blog.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.constant.AttributeConstant;
import com.blog.model.Article;
import com.blog.model.dto.ArticleDto;
import com.blog.service.ArticleService;
import com.blog.service.WebAppService;
import com.blog.util.Pager;
import com.blog.util.StringUtil;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService articleservice;
	
	@Autowired
	private WebAppService webappservice;
   
	@RequestMapping(value="article/{articleId:[0-9]+}")
	public String getDetails(@PathVariable("articleId")Integer articleId,ModelMap map){
		ArticleDto article = articleservice.getArticle(articleId);
		map.addAttribute(AttributeConstant.MAIN_PAGE, "user/article/detail.jsp");
		map.addAttribute(AttributeConstant.WEB_APP_DTO, webappservice.getWebDtoWebApp(webappservice.getWebAppDtos().get(0).getId()));
		if(StringUtil.isNotEmpty(article.getTitle())){
			//点击量+1
			articleservice.updateClicks(article.getClicks()+1, articleId);
			//更新以便稍后用到的时候显示
			article.setClicks(article.getClicks()+1);
			map.addAttribute(AttributeConstant.ARTICLE, article);
			map.addAttribute("preArticle", articleservice.getPreArticle(articleId));
			map.addAttribute("nextArticle", articleservice.getNextArticle(articleId));
		}else{
			map.addAttribute(AttributeConstant.ERROR, "没有此文章");
		}
		return "index";
	}
	
	// 搜索操作
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String search(String content,ModelMap map){
		Article article = new Article();
		article.setTitle(content);
		List<ArticleDto> list = articleservice.searchArticles(article);
		//博主名称
		map.addAttribute(AttributeConstant.WEB_APP_DTO, webappservice.getWebDtoWebApp(webappservice.getWebAppDtos().get(0).getId()));
		if(list.size()==0){
			map.addAttribute(AttributeConstant.RETURN_INFO, "没有找到该内容");
		}else{
			//搜索结果不分页
			Pager pager = new Pager(1, list.size(), list.size());
			map.addAttribute(AttributeConstant.PAGER, pager);
			map.addAttribute(AttributeConstant.ARTICLES, list);
			map.addAttribute(AttributeConstant.RETURN_INFO, "搜索内容已全部显示");
		}
		map.addAttribute(AttributeConstant.MAIN_PAGE, "user/article/articlelist.jsp");
		//标识，用来区分是否经过搜索
		map.addAttribute("search", "search");
		return "index";
	}
	
	//直接点击搜索，跳至首页
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String showsearch(ModelMap map){
		return "redirect:/";
	}
}

