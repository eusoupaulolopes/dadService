package com.ufrn.dad.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ufrn.dad.dao.ComponenteDao;
import com.ufrn.dad.dao.GenericDao;
import com.ufrn.dad.model.ComponenteCurricular;
import com.ufrn.dad.model.Unidade;

@Repository
public class ComponenteDaoImpl extends GenericDao implements ComponenteDao {

	@Override
	public List<ComponenteCurricular> findAll() {
		String sql = "SELECT * FROM componente_curricular "
				+ "left join unidade on unidade.id_unidade=componente_curricular.id_unidade "
				+ "where unidade.id_unidade is not NULL";
		;

		List<ComponenteCurricular> componentes = new ArrayList<>();

		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					ComponenteCurricular componente = new ComponenteCurricular();
					componente.setId(rs.getInt("id_componente_curricular"));
					componente.setCodigo(rs.getString("codigo"));
					componente.setNomeComponenteCurricular(rs.getString("nome_componente_curricular"));
					Unidade unidade = new Unidade();
					unidade.setId(rs.getInt("id_unidade"));
					unidade.setLotacao(rs.getString("lotacao"));
					componente.setUnidade(unidade);
					componentes.add(componente);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return componentes;
	}

	@Override
	public ComponenteCurricular findById(Integer id) {
		String sql = "";
		ComponenteCurricular componente = null;
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(sql)) {

				ResultSet rs = ps.executeQuery();
				componente = new ComponenteCurricular();
				componente.setId(rs.getInt("id_componente_curricular"));
				componente.setCodigo(rs.getString("codigo"));
				componente.setNomeComponenteCurricular(rs.getString("nome_componente_curricular"));
				Unidade unidade = new Unidade();
				unidade.setId(rs.getInt("id_unidade"));
				unidade.setLotacao(rs.getString("lotacao"));
				componente.setUnidade(unidade);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return componente;
	}

	@Override
	public ComponenteCurricular save(ComponenteCurricular componente) {
		String sql = "REPLACE componente_curricular "
				+ "(id_componente_curricular, codigo, nome_componente_curricular, id_unidade)" + "VALUES (?, ?, ?, ?)";

		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, componente.getId());
				ps.setString(2, componente.getCodigo());
				ps.setString(3, componente.getNomeComponenteCurricular());
				ps.setInt(4, componente.getUnidade().getId());

				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return componente;

	}

	@Override
	public void delete(ComponenteCurricular componente) {
		String sql = "DELETE FROM componente_curricular WHERE id_componente_curricular=?";
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, componente.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public List<ComponenteCurricular> findAllByNomeComponente(String nome_componente_curricular) {
		String sql = "SELECT * FROM componente_curricular WHERE nome_componente_curricular LIKE ?";

		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, "%" + nome_componente_curricular + "%");
				ResultSet rs = ps.executeQuery();
				List<ComponenteCurricular> componentes = new ArrayList<>();
				while (rs.next()) {
					ComponenteCurricular componente = new ComponenteCurricular();
					componente.setId(rs.getInt("id_componente_curricular"));
					componente.setCodigo(rs.getString("codigo"));
					componente.setNomeComponenteCurricular(rs.getString("nome_componente_curricular"));
					Unidade unidade = new Unidade();
					unidade.setId(rs.getInt("id_unidade"));
					unidade.setLotacao(rs.getString("lotacao"));
					componente.setUnidade(unidade);
					componentes.add(componente);
				}
				
				
				return componentes;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
		
	}

}
