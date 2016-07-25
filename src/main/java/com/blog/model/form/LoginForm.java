package com.blog.model.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.model.User;
import com.blog.service.UserService;

/*
 * 用于绑定登陆时提交的表单数据
 */
public class LoginForm {

	
	private String username;
	private String password;
	private String code;
	private String remember = "no";


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}

}
