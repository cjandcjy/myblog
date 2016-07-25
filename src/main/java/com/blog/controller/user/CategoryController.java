package com.blog.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.constant.AttributeConstant;
import com.blog.model.Category;
import com.blog.model.dto.ArticleLiteDto;
import com.blog.model.dto.CategoryDto;
import com.blog.service.ArticleService;
import com.blog.service.CategoryService;
import com.blog.service.WebAppService;
import com.blog.util.StringUtil;

@Controller
@RequestMapping(value="category")
public class CategoryController {
   
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	WebAppService webAppService;
	
	@Autowired
	ArticleService articleService;
	
	@RequestMapping(value="list")
	public String list(ModelMap map){
        map.addAttribute(AttributeConstant.WEB_APP_DTO,webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));
		List<CategoryDto> categoryDtos = categoryService.getCategories();
		map.addAttribute(AttributeConstant.CATEGORIES, categoryDtos);
		map.addAttribute(AttributeConstant.MAIN_PAGE, "user/category/categoryList.jsp");
		return "index";
	}
	
	@RequestMapping(value="/{CategoryId:[0-9]+}")
	public String detail(@PathVariable("CategoryId")Integer categoryId,ModelMap map){
        map.addAttribute(AttributeConstant.WEB_APP_DTO,webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));
        map.addAttribute(AttributeConstant.MAIN_PAGE, "user/category/detail.jsp");
        Category category = categoryService.getCategory(categoryId);
        if(StringUtil.isNotEmpty(category.getName())){
        	List<ArticleLiteDto> articles = articleService.getArticlesByCategory(categoryId);
        	if(articles.size()==0){
        		articles = null;
        	}
        	map.addAttribute(AttributeConstant.ARTICLES, articles);
        	map.addAttribute(AttributeConstant.CATEGORY, category);
        }else{
        	map.addAttribute(AttributeConstant.ERROR, "找不到该分类");
        }
        return "index";
	}
}
