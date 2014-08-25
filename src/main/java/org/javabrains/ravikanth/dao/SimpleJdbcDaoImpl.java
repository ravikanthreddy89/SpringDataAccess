package org.javabrains.ravikanth.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class SimpleJdbcDaoImpl extends SimpleJdbcDaoSupport {

	public int getCircleCount(){
		String sql="select count(*) from circle";
		return getSimpleJdbcTemplate().queryForInt(sql);
	}
}
