package com.blog.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.constant.AttributeConstant;
import com.blog.model.dto.ArticleLiteDto;
import com.blog.service.ArticleService;
import com.blog.service.WebAppService;


@Controller
public class ArchiveController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private WebAppService webAppService;
     //显示归档页面    
    @RequestMapping("/archive")
    public String archive(ModelMap model){
        List<ArticleLiteDto> articleLiteDtos = articleService.getArchive();
        model.addAttribute(AttributeConstant.WEB_APP_DTO,webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));
        model.addAttribute(AttributeConstant.ARTICLES, articleLiteDtos);
        model.addAttribute(AttributeConstant.MAIN_PAGE, "user/archive/detail.jsp");
        return "index";
    }
}
