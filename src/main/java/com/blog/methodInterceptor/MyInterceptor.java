package com.blog.methodInterceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.service.UserService;

public class MyInterceptor implements MethodInterceptor{
	

	@Override
	public Object invoke(MethodInvocation i) throws Throwable {
		     System.out.println("method "+i.getMethod()+" is called on "+
		                        i.getThis()+" with args "+i.getArguments());
		     Object ret=i.proceed();
		     System.out.println("method "+i.getMethod()+" returns "+ret);
		     return ret+",interceptor";
		   
	}
}
