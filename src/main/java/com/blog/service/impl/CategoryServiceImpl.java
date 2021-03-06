package com.blog.service.impl;


import org.springframework.stereotype.Service;

import com.blog.dao.CategoryDao;
import com.blog.model.Category;
import com.blog.model.dto.CategoryDto;
import com.blog.service.CategoryService;
import com.blog.util.Pager;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SuperS on 15/12/13.
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    @Override
    public List<CategoryDto> getCategories() {
        List<CategoryDto> categories = null;
        try {
            categories = categoryDao.all();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void updateCategory(Category category) {
        try {
            categoryDao.update(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveCategory(Category category) {
        try {
            categoryDao.save(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        try {
            categoryDao.delete(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CategoryDto> getPageCategories(Pager pager) {
        List<CategoryDto> categoryDtos = null;
        try {
            categoryDtos = categoryDao.pagenation(pager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryDtos;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = categoryDao.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Category getCategory(int categoryId) {
        Category category = null;
        try {
            category = categoryDao.get(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean exist(int categoryId) {
        int state = 0;
        try {
            state = categoryDao.exist(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state > 0 ? true : false;
    }
}
