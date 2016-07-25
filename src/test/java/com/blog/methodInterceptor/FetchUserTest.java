package com.blog.methodInterceptor;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class FetchUserTest {
	
	static FetchUser fetchuser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//        ClassPathResource resource = new ClassPathResource("spring.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-new.xml");
		fetchuser = (FetchUser) context.getBean("fetchuser");
	}



	@Test
	public void test() {
		
		String string = fetchuser.getUsers();
		System.out.println(string);
	}

}
