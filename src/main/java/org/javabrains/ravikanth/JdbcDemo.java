package org.javabrains.ravikanth;

import org.javabrains.ravikanth.dao.JdbcDaoImpl;
import org.javabrains.ravikanth.model.Circle;

public class JdbcDemo {

	public static void main(String [] args){
		JdbcDaoImpl jdbcDaoImpl=new JdbcDaoImpl();
		Circle circle=jdbcDaoImpl.getCircle(2);
		
		System.out.println("Circle name : "+circle.getName());
	}
}
