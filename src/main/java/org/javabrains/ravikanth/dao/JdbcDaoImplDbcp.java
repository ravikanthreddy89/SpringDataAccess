package org.javabrains.ravikanth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.javabrains.ravikanth.model.Circle;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcDaoImplDbcp {

	private JdbcTemplate jdbcTemplate;
	private DataSource  dbcpDataSource;

	public JdbcDaoImplDbcp(){
				
	}	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	public DataSource getDbcpDataSource() {
		return dbcpDataSource;
	}

	public void setDbcpDataSource(DataSource dbcpDataSource) {
		this.dbcpDataSource = dbcpDataSource;
	}

	public Circle getCircle(int id){
		Circle circle=null;
		Connection con=null;
		
		try{
			
			//get the connection
			con=getDbcpDataSource().getConnection();
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

	
	public int getCircleCount(){
		if(getJdbcTemplate()==null)setJdbcTemplate(new JdbcTemplate());		
		getJdbcTemplate().setDataSource(dbcpDataSource);
		
		String sql="select count(*) from circle";
		return getJdbcTemplate().queryForInt(sql);
	}
}
