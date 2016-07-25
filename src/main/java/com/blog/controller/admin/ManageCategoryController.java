package com.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.constant.AttributeConstant;
import com.blog.model.Category;
import com.blog.model.dto.UserDto;
import com.blog.service.CategoryService;
import com.blog.util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by cj on 16/6/20.
 *
 * 管理员 分类编辑页面
 */
@Controller
@RequestMapping(value="/manager/category")
public class ManageCategoryController {
    @Autowired
    private CategoryService categoryService;
    
    
    //显示分类编辑页面
    @RequestMapping(method = RequestMethod.GET)
    public String showCategoriesPage(ModelMap model, HttpSession session) {
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "category/editor.jsp");
        return "admin/index";
    }
    
    
    //创建分类
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createAction(String categoryName, ModelMap model, HttpServletResponse response) {
    	PrintWriter writer = null;
    	try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (StringUtil.isNotEmpty(categoryName)) {
            Category category = new Category();
            category.setName(categoryName);
            categoryService.saveCategory(category);
            writer.write("创建分类成功！");
        } else {
            writer.write("分类名称为填写");
        }
    }
    
    //更新分类
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void upDateAction(String categoryName,Integer categoryid,HttpServletResponse response){
    	PrintWriter writer = null;
    	try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Category category = new Category();
        category.setName(categoryName);
        category.setId(categoryid);
        categoryService.updateCategory(category);
        writer.write("修改成功!");
    }

    //删除分类
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void  deleteAction(Integer categoryid,HttpServletResponse response){
    	PrintWriter writer = null;
    	try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        Category category = categoryService.getCategory(categoryid);
        if(StringUtil.isNotEmpty(category.getName())){
            categoryService.deleteCategory(categoryid);
            writer.write("删除成功！");
        }else{
            writer.write("找不到该分类！");
        }
    }

}
