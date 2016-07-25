package com.blog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.blog.model.WebApp;
import com.blog.model.dto.WebAppDto;
/*
 * created by cj 
 * 2016/6/8
 */
@Repository
public interface WebAppDao {
    public void save(WebApp webApp) throws Exception;
    public void update(WebApp webApp) throws Exception;
    public WebAppDto getWebDto(Integer id) throws Exception;
    public Integer getArticlesView() throws Exception;
    public Integer count() throws Exception;
    public List<WebApp> getWebDtos() throws Exception;
}
