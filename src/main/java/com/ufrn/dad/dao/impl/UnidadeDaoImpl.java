package com.ufrn.dad.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ufrn.dad.dao.UnidadeDao;
import com.ufrn.dad.model.Unidade;

@Repository
public class UnidadeDaoImpl implements UnidadeDao {
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Unidade> findAll() {

		String sql = "SELECT * FROM UNIDADE";
		Connection conn = null;
		List<Unidade> unidades = new ArrayList<Unidade>();

		try {
			conn = dataSource.getConnection();
			Statement st = conn.createStatement();
			st.setFetchSize(50);
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Unidade unidade = new Unidade();
				unidade.setId(rs.getInt("id_unidade"));
				unidade.setLotacao(rs.getString("lotacao"));
				unidades.add(unidade);
			}

			st.close();
			rs.close();

			return unidades;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public Unidade findById(Integer id) {
		String sql = "SELECT * FROM UNIDADE WHERE id_unidade = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Unidade unidade = null;
			if (rs.next()) {
				unidade = new Unidade();
				unidade.setId(rs.getInt("id_unidade"));
				unidade.setLotacao(rs.getString("lotacao"));
			}
			ps.close();
			rs.close();
			return unidade;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public Unidade save(Unidade u) {
		String sql = "INSERT INTO UNIDADE" + "(id_unidade, lotacao) VALUES (?, ?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ps.setString(2, u.getLotacao());
			ps.executeUpdate();
			ps.close();
			return u;
		} catch (SQLException e) {
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
		String sql = "DELETE FROM UNIDADE WHERE id_unidade = ? and lotacao = ?";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ps.setString(2, u.getLotacao());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			} 
		}

	}

}
