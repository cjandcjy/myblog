package com.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.constant.AttributeConstant;
import com.blog.model.About;
import com.blog.model.dto.UserDto;
import com.blog.service.AboutService;
import com.blog.util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by SuperS on 16/3/1.
 * 管理员 About页面
 */
@Controller
@RequestMapping("/manager/about")
public class ManageAboutController {
    @Autowired
    private AboutService aboutService;

    //显示About页面
    @RequestMapping(method = RequestMethod.GET)
    public String showAbout(ModelMap model,HttpSession session){
        String path;
        //about表中 只有一条 信息 如果>0 则说明存在 关于的信息,那么跳转
        if(aboutService.count()>0){
            path="redirect:/manager/about/update";
        }else{
            model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
            model.addAttribute(AttributeConstant.MAIN_PAGE,"about/editor.jsp");
            path = "admin/index";
        }
        return path;
    }
    //显示更新About页面
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String showUpdate(ModelMap model,HttpSession session){
        //因为数据库保持只有1个about记录超过了会出问题.
    	About about = aboutService.list().get(0);     
        model.addAttribute(AttributeConstant.ABOUT,about);
        model.addAttribute(AttributeConstant.MAIN_PAGE,"about/editor.jsp");
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        return "admin/index";
    }

    //更新About操作
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateAction(ModelMap model,HttpServletResponse response,About about){
    	
    	PrintWriter writer = null;
    	try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (StringUtil.isNotEmpty(about.getMarkDown()) && StringUtil.isNotEmpty(about.getContent())) {
            //因为数据库保持只有1个about记录所以可以这样获取
            about.setId(aboutService.list().get(0).getId());
            aboutService.upDate(about);
            writer.write("更新成功!");
        }else {
            writer.write("修改失败，不能为空！");
        }
    }
    //创建About操作
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public void createAction(About about,HttpServletResponse response){
    	PrintWriter writer = null;
    	try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(StringUtil.isNotEmpty(about.getContent())&&StringUtil.isNotEmpty(about.getMarkDown())){
            aboutService.save(about);
            writer.write("新增信息成功！");
        }else{
        	writer.write("信息不完全！");
        }
    }
}
