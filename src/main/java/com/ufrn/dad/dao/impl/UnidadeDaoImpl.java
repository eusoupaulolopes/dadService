package com.ufrn.dad.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ufrn.dad.dao.GenericDao;
import com.ufrn.dad.dao.UnidadeDao;
import com.ufrn.dad.model.Unidade;

/**
 * Implementacao do DAO responsável pelas requisicoes a Unidades
 * @author Paulo
 *
 */
@Repository
public class UnidadeDaoImpl extends GenericDao implements UnidadeDao {
	

	@Override
	public List<Unidade> findAll() {

		String sql = "SELECT * FROM unidade";
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
		String sql = "SELECT * FROM unidade WHERE id_unidade = ?";
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
		String sql = "REPLACE INTO unidade" + "(id_unidade, lotacao) VALUES (?, ?)";
		
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ps.setString(2, u.getLotacao());
			ps.executeUpdate();
			ps.close();
			return u;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}

	@Override
	public void delete(Unidade u) {
		String sql = "DELETE FROM unidade WHERE id_unidade = ? and lotacao = ?";
		
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
