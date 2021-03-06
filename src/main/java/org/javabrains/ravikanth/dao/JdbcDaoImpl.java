package org.javabrains.ravikanth.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.javabrains.ravikanth.model.Circle;
import org.springframework.stereotype.Component;

public class JdbcDaoImpl {
	
	private DataSource dataSource;
	
	public JdbcDaoImpl(){
		
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	
	public Circle getCircle(int id){
		Circle circle=null;
		Connection con=null;
		
		try{
			
			//get the connection
			con=getDataSource().getConnection();
			//Prepare the statement
			PreparedStatement stmt=con.prepareStatement("select * from circle where id =?");
			stmt.setInt(1, id);
			
			//Excute the query
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				String name=rs.getString("name");
				circle=new Circle(id, name);
			}
			
			//close the statement and Resultset
			stmt.close();
			rs.close();
			
			return circle;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}		
		return circle;
	}

}
