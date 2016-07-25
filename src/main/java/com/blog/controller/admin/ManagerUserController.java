package com.blog.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.constant.AttributeConstant;
import com.blog.model.User;
import com.blog.model.dto.UserDto;
import com.blog.service.UserService;
import com.blog.util.Md5Utile;
import com.blog.util.StringUtil;


@Controller
@RequestMapping("manager/user")
public class ManagerUserController {
    @Autowired
    private UserService userService;

    //显示用户列表
    @RequestMapping(method = RequestMethod.GET)
    public String showUsers(ModelMap model, HttpSession session) {
        model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
        model.addAttribute(AttributeConstant.MAIN_PAGE, "user/editor.jsp");
        model.addAttribute(AttributeConstant.USERS, userService.getUsers());
        return "admin/index";
    }

    //更新用户
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public void createAction(User user, HttpServletResponse response) {
    	
    	PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        if (StringUtil.isNotEmpty(user.getUsername()) && StringUtil.isNotEmpty(user.getPassword()) && StringUtil.isNotEmpty(user.getNickname()) && StringUtil.isNotEmpty(user.getEmail())) {
            if (!userService.userIsNotEmpty(user.getUsername())) {
                user.setPassword(Md5Utile.MD5(user.getPassword()));
                user.setImagePath("/images/users/tx.jpg");
                userService.saveUser(user);
                writer.write("用户添加成功!");
            } else {
            	writer.write("用户名已存在!");
            }           
        } else {
            writer.write("有未填写用户信息!");
        }
    }

   
    //更用户操作
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void updateAction(User user, HttpServletResponse response,HttpSession session) {
    	UserDto userDto = (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER);
    	PrintWriter writer = null;
    	try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        if (StringUtil.isNotEmpty(user.getUsername()) && StringUtil.isNotEmpty(user.getPassword()) && StringUtil.isNotEmpty(user.getNickname()) && StringUtil.isNotEmpty(user.getEmail())) {
            //如果管理员没有编辑选择用户的密码,就不在对密码进行MD5加密
            if (!user.getPassword().equals(userService.getUser(user.getId()).getPassword())) {
                user.setPassword(Md5Utile.MD5(user.getPassword()));
            }
            userService.updateUser(user);
            writer.write("修改用户成功！");
            //判断更新用户 是否为 当前登陆用户 如果是 需要更新当前登陆用户的 显示信息.
            if (user.getId() == userDto.getId()) {
                UserDto currentUser = userService.login(user);
                session.setAttribute(AttributeConstant.CURRENT_USER, currentUser);
            }
        } else {
             writer.write("有未填写用户信息");
        }
    }

    //根据ID删除用户
    @RequestMapping(value = "/delete")
    public void deleteAction(HttpServletResponse response,Integer userId) {      
        User user = userService.getUser(userId);
        if (StringUtil.isNotEmpty(user.getUsername())) {
            userService.deleteUser(userId);
           try {
			response.getWriter().write("删除用户成功!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        } else {
        	try {
				response.getWriter().write("找不到该用户!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }      
    }
}
