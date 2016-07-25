package com.blog.controller.admin;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.blog.model.dto.UserDto;
import com.blog.model.dto.WebAppDto;
import com.blog.service.ArticleService;
import com.blog.service.CategoryService;
import com.blog.service.WebAppService;
import com.blog.util.Pager;
import com.blog.util.StringUtil;


@Controller
@RequestMapping(value="/manager/article") 
public class ManagerArticleController {
   
	@Autowired
	ArticleService articleservice;
	@Autowired
	WebAppService webappservice;
	@Autowired
	CategoryService categoryservice;
	 
	@RequestMapping(method=RequestMethod.GET)
	public String editArticle(@RequestParam(defaultValue="1")Integer pageIndex, ModelMap map,HttpSession session){
		WebAppDto webAppDto = webappservice.getWebDtoWebApp(webappservice.getWebAppDtos().get(0).getId());
		Pager pager = new Pager(pageIndex, webAppDto.getAdminPageArticleSize(), articleservice.count());
		List<ArticleDto> articles = articleservice.getPageArticles(pager);
		map.addAttribute(AttributeConstant.WEB_APP_DTO, webAppDto);
		map.addAttribute(AttributeConstant.PAGER, pager);
		map.addAttribute(AttributeConstant.CURRENT_USER, session.getAttribute(AttributeConstant.CURRENT_USER));
		map.addAttribute(AttributeConstant.ARTICLES,articles);
		map.addAttribute(AttributeConstant.MAIN_PAGE, "article/listArticle.jsp");
		return "admin/index";
	}
	
	@RequestMapping(value="/update/{articleId:[0-9]+}",method=RequestMethod.GET)
	public String showupdate(@PathVariable("articleId")Integer articleId,ModelMap map,HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER);
		map.addAttribute(AttributeConstant.CURRENT_USER,userDto);
        ArticleDto articleDto = articleservice.getArticle(articleId);
        if(StringUtil.isNotEmpty(articleDto.getTitle())){
        	map.addAttribute(AttributeConstant.CATEGORIES, categoryservice.getCategories());
        	map.addAttribute(AttributeConstant.ARTICLE, articleDto);
        	map.addAttribute(AttributeConstant.MAIN_PAGE, "article/editArticle.jsp");
        }else{
        	Pager pager = new Pager(1,10, articleservice.count());
        	map.addAttribute(AttributeConstant.ERROR, "找不到该文章，或已被删除");
        	map.addAttribute(AttributeConstant.PAGER, pager);
        	map.addAttribute(AttributeConstant.ARTICLES, articleservice.getPageArticles(pager));
        	map.addAttribute(AttributeConstant.MAIN_PAGE, "article/listArticle.jsp");
        }
        return "admin/index";
	}
	
	@RequestMapping(value="/update/{articleId:[0-9]+}",method=RequestMethod.POST)
	public String update(Article article,@PathVariable("articleId")Integer articleId,ModelMap map,HttpSession session){
		 String path;
	        if (StringUtil.isNotEmpty(article.getTitle()) && StringUtil.isNotEmpty(article.getMarkDown()) && StringUtil.isNotEmpty(article.getRemark())) {
	            article.setClicks(articleservice.getArticle(articleId).getClicks());
	            article.setPubDate(articleservice.getArticle(articleId).getPubDate());
	            articleservice.updateArticle(article);
	            path = "redirect:/manager/article";
	        } else {
	            map.addAttribute(AttributeConstant.ERROR, "有未填选项,请核对后重新发布文章!");
	            map.addAttribute(AttributeConstant.MAIN_PAGE, "article/editArticle.jsp");
	            map.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
	            map.addAttribute(AttributeConstant.ARTICLE, articleservice.getArticle(articleId));
	            map.addAttribute(AttributeConstant.CATEGORIES, categoryservice.getCategories());
	            path = "admin/index";
	        }
	        return path;
	}
	
	 //显示创建页面
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String showCreatePage(ModelMap model, HttpSession session) {
    	UserDto userDto = (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER);
        model.addAttribute(AttributeConstant.MAIN_PAGE, "article/editArticle.jsp");
        model.addAttribute(AttributeConstant.USER, userDto);
        model.addAttribute(AttributeConstant.CATEGORIES, categoryservice.getCategories());
        return "admin/index";
    }
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
    public void createAction(Article article, ModelMap model, HttpSession session,HttpServletResponse response) {
        article.setClicks(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setPubDate(formatter.format(new Date()));
        System.out.println(article.getUserId());
        System.out.println(article.getTitle());
        System.out.println(article.getRemark());
        System.out.println(article.getContent());
        System.out.println(article.getMarkDown());
        if (StringUtil.isNotEmpty(article.getTitle()) && StringUtil.isNotEmpty(article.getMarkDown()) && StringUtil.isNotEmpty(article.getRemark())) {           
        	
        	articleservice.saveArticle(article);            				
		    String json = "{\"success\":true,\"msg\":\"提交成功!\"}";	
		    
		    try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		    
        } else {    
        	String json = "{\"success\":true,\"msg\":\"提交失败!\"}";
            	try {
					response.getWriter().write(json);
				} catch (IOException e) {
					e.printStackTrace();
				}	
        }
    }
	
	@RequestMapping(value="/delete")
	public void delete(Integer articleid,HttpServletResponse response){
		articleservice.deleteArticle(articleid);
		try {
			response.getWriter().write("删除成功");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
     
    //搜索 文章
    @RequestMapping("search")
    public String search(String content, ModelMap model, HttpSession session) {
        Article article = new Article();
        article.setTitle(content);
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        List<ArticleDto> articleDtos = articleservice.searchArticles(article);
        Pager pager = new Pager(1, 10, articleservice.searchArticles(article).size());
        if (articleDtos.size() > 0) {
            model.addAttribute(AttributeConstant.ARTICLES, articleDtos);
        } else {
            model.addAttribute(AttributeConstant.ARTICLES, null);
        }
        model.addAttribute(AttributeConstant.PAGER, pager);

        model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/listArticle.jsp");
        return "admin/index";
    }
	
}
