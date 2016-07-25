package com.blog.service;

import java.util.List;

import com.blog.model.Article;
import com.blog.model.dto.ArticleDto;
import com.blog.model.dto.ArticleLiteDto;
import com.blog.util.Pager;

public interface ArticleService {
	  //获取文章
      public ArticleDto getArticle(Integer id);
      //更新文章
      public void updateArticle(Article article);
      //新增文章
      public void saveArticle(Article article);
      //删除文章
      public void deleteArticle(Integer id);
      //按标题搜索文章
      public List<ArticleDto> searchArticles(Article article);
      //按类获取文章
      public List<ArticleLiteDto> getArticlesByCategory(int categoryid);
      //按页获取文章
      public  List<ArticleDto> getPageArticles(Pager pager);
      //归档文章列表（获取所有文章的articleid，title，clicks）
      public List<ArticleLiteDto> getArchive();
      //获取上一篇文章
      public ArticleLiteDto getPreArticle(Integer id);
      //获取下一篇文章
      public ArticleLiteDto getNextArticle(Integer id);
      //获取文章总数
      public int count();
      //更新点击量
      public void updateClicks(Integer clicks,Integer id);
}
