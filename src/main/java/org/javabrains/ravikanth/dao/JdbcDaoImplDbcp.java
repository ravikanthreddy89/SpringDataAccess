package org.javabrains.ravikanth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.javabrains.ravikanth.model.Circle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
	
	public String getCirlceName(int id){
		getJdbcTemplate().setDataSource(dbcpDataSource);
		String sql="select name from circle where id = ?";
		return getJdbcTemplate().queryForObject(sql,new Object[] { id},  String.class);
	}
	
	public Circle getCircleWithMapper(int id){
		getJdbcTemplate().setDataSource(dbcpDataSource);
		String sql="select * from circle where id = ?";
		return getJdbcTemplate().queryForObject(sql, new Object[] {id}, new CircleMapper());
	}
	
	public List<Circle> getAllCircles(){
		getJdbcTemplate().setDataSource(dbcpDataSource);
		String sql="select * from circle";
		return getJdbcTemplate().query(sql, new CircleMapper());
	}
	
	
	public void insertCircle(Circle c){
		String sql="insert into circle values(?, ?)";
		getJdbcTemplate().setDataSource(dbcpDataSource);
		getJdbcTemplate().update(sql, new Object[] {c.getId(), c.getName()});
	}
	
	public void createTriangle(){
		String sql="create table triangle( id int , name varchar(20))";
		getJdbcTemplate().execute(sql);
	}
	
	private static final class CircleMapper implements RowMapper<Circle>{

		public Circle mapRow(ResultSet resultSet, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			Circle circle = new Circle();
			circle.setId(resultSet.getInt("id"));
			circle.setName(resultSet.getString("name"));
			return circle;
		}
		
	}
}
