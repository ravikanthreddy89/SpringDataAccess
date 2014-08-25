package org.javabrains.ravikanth;

import org.javabrains.ravikanth.dao.JdbcDaoImpl;
import org.javabrains.ravikanth.dao.JdbcDaoImplDbcp;
import org.javabrains.ravikanth.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

	public static void main(String [] args){
		
		ApplicationContext beanFactory = new ClassPathXmlApplicationContext("spring.xml");
		JdbcDaoImplDbcp jdbcDaoImplDbcp=beanFactory.getBean("JdbcDaoImplDbcp", JdbcDaoImplDbcp.class);
		Circle circle=jdbcDaoImplDbcp.getCircle(2);
		
		System.out.println("Circle name : "+circle.getName());
		System.out.println("Number of circles : "+jdbcDaoImplDbcp.getCircleCount());
		
		System.out.println("Circle Name : "+jdbcDaoImplDbcp.getCirlceName(7));
		
		Circle c=jdbcDaoImplDbcp.getCircleWithMapper(6);
		System.out.println("ID : "+c.getId());
		System.out.println("Name : "+c.getName());
		
		jdbcDaoImplDbcp.insertCircle(new Circle(8, "Eigth Circle"));
		//jdbcDaoImplDbcp.createTriangle();
		
		jdbcDaoImplDbcp.insertCircleNamedParameter(new Circle(9, "Ninth Circle"));
		
		System.out.println("Total number of circles : "+jdbcDaoImplDbcp.getAllCircles().size());
	}
	
}
