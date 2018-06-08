package com.ufrn.dad.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.ufrn.dad.dao.UnidadeDao;
import com.ufrn.dad.model.Unidade;

public class UnidadeDaoImpl implements UnidadeDao {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Unidade> getAll() {
		return null;
	}

	@Override
	public Unidade getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Unidade u) {
		String sql = "INSERT INTO UNIDADE" + 
				"(id_unidade, lotacao) VALUES (?, ?)";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ps.setString(2,  u.getLotacao());
			ps.executeUpdate();
			ps.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.err.print(e.getMessage());
				}
			}
		}
		
		
		
	}

	@Override
	public void update(Unidade u) {
		
	}

	@Override
	public void delete(Unidade u) {
		// TODO Auto-generated method stub
		
	}
	
	
}
