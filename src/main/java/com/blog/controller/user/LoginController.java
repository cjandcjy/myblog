package com.blog.controller.user;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.constant.AttributeConstant;
import com.blog.model.User;
import com.blog.model.dto.UserDto;
import com.blog.model.form.LoginForm;
import com.blog.service.UserService;
import com.blog.util.IdentifyCodeUtil;
import com.blog.util.Md5Utile;

import sun.rmi.log.LogOutputStream;

@Controller
public class LoginController {
	
	@Autowired
	UserService userservice;
	
	//显示登录页面
   @RequestMapping(value="/login",method=RequestMethod.GET)
   public String showlogin(HttpServletRequest request,ModelMap map){
	   Cookie[] cookies = request.getCookies();
	   User user = new User();
	   String username = "";
	   String password = "";
	   if(cookies!=null){
		   for(Cookie cookie:cookies){
			   if("username".equals(cookie.getName())){
				   username = cookie.getValue();				   
				   user.setUsername(username);
			   }
			   if("password".equals(cookie.getName())){
				   password = Md5Utile.MD5(cookie.getValue());				   
				   user.setPassword(password);
			   }
		   }
	   }
	   if(!username.equals("")&&!password.equals("")){
           UserDto userDto = userservice.login(user);
           if(userDto!=null){
	           request.getSession().setAttribute(AttributeConstant.CURRENT_USER, userDto);
	           return "redirect:/manager";
           }else{
        	   map.addAttribute(new LoginForm());
   	   		   return "user/login/login";
           }
	   }else{
		   	map.addAttribute(new LoginForm());
	   		return "user/login/login";
	   }
   }
   
   //登陆操作
   @RequestMapping(value="/login",method=RequestMethod.POST)
   public String login(LoginForm loginform,ModelMap map,HttpSession session,HttpServletResponse response){
	    if(loginform.getCode().equalsIgnoreCase((String) session.getAttribute("code"))){
	    	User user = new User();
	    	user.setUsername(loginform.getUsername());
	    	user.setPassword(Md5Utile.MD5(loginform.getPassword()));
	    	UserDto userDto = userservice.login(user);
	    	if(userDto!=null){
	    		if(loginform.getRemember().equals("yes")){ 
	    			//记住我操作
	    			rememberMe(loginform.getUsername(),loginform.getPassword(),response);
	    		}
	    		session.setAttribute(AttributeConstant.CURRENT_USER, userDto);
	    		//跳转
	    		return "redirect:/manager";
	    	}else{
	    		map.addAttribute(AttributeConstant.ERROR, "用户名或者密码错误");
	    	}
	    }else{
	    	map.addAttribute(AttributeConstant.ERROR, "验证码错误");
	    }
	    return "user/login/login";
   }
   
   
   //登出操作
   @RequestMapping(value="logout")
   public String logout(HttpSession session){
	   session.setAttribute(AttributeConstant.CURRENT_USER, null);
	   return "redirect:/";
   }


   //获取验证码图片
   @RequestMapping(value="/login/codeimg")
   public void creatCode(ModelMap map,HttpServletResponse response,HttpSession session){
	   IdentifyCodeUtil codeutil = new IdentifyCodeUtil();
	   //生成验证码
	   String code = codeutil.RandomString();
	   //保存到会话域
	   session.setAttribute("code", code);
	   //生成图片
	   codeutil.DrawImage(code);	   
	   try {
		codeutil.write(response.getOutputStream());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   
   private void rememberMe(String username, String password,HttpServletResponse response) {
	   Cookie usernamecookie = new Cookie("username", username);
	   Cookie passwordcookie = new Cookie("password", password);
	   usernamecookie.setMaxAge(AttributeConstant.DAY_TIME*7);
	   passwordcookie.setMaxAge(AttributeConstant.DAY_TIME*7);
	   response.addCookie(usernamecookie);
	   response.addCookie(passwordcookie);
   }
}
