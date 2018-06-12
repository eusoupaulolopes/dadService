package com.ufrn.dad.dao;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDao {
	
	@Autowired
	protected DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
