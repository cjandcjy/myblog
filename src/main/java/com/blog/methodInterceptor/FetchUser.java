package com.blog.methodInterceptor;

import java.util.List;


import com.blog.model.User;
import com.blog.service.UserService;
import com.blog.service.impl.UserServiceImpl;

public class FetchUser implements IFetchUser{
	
	
	public   String getUsers(){
		return "method processing";
	}
	
	
}
