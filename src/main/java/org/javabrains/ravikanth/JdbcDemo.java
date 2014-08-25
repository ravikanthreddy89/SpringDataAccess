package org.javabrains.ravikanth;

import org.javabrains.ravikanth.dao.JdbcDaoImpl;
import org.javabrains.ravikanth.dao.JdbcDaoImplDbcp;
import org.javabrains.ravikanth.dao.SimpleJdbcDaoImpl;
import org.javabrains.ravikanth.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

	public static void main(String [] args){
		
		ApplicationContext beanFactory = new ClassPathXmlApplicationContext("spring.xml");
		SimpleJdbcDaoImpl simpleJdbcDaoImpl=beanFactory.getBean("SimpleJdbcDaoImpl", SimpleJdbcDaoImpl.class);
		
		System.out.println("Total number of circles : "+simpleJdbcDaoImpl.getCircleCount());
	}
	
}
