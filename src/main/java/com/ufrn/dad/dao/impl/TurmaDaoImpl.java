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
import com.ufrn.dad.dao.TurmaDao;
import com.ufrn.dad.model.ComponenteCurricular;
import com.ufrn.dad.model.Turma;
import com.ufrn.dad.model.Unidade;

/**
 * 
 * @author Paulo
 *
 */
@Repository
public class TurmaDaoImpl extends GenericDao implements TurmaDao {

	@Override
	public List<Turma> findAll() {
		String sql = "SELECT * FROM turma "
				+ "turma left join componente_curricular on turma.id_componente=componente_curricular.id_componente_curricular"
				+ " left join unidade on componente_curricular.id_unidade=unidade.id_unidade";

		List<Turma> turmas = new ArrayList<Turma>();

		try (Connection conn = dataSource.getConnection()) {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Turma turma = new Turma();
				turma.setId(rs.getInt("id_turma"));
				turma.setAno(rs.getString("ano"));
				turma.setPeriodo(rs.getString("periodo"));
				turma.setNivel(rs.getString("nivel"));
				ComponenteCurricular cc = new ComponenteCurricular();
				cc.setId(rs.getInt("id_componente_curricular"));
				cc.setNomeComponenteCurricular(rs.getString("nome_componente_curricular"));
				cc.setCodigo(rs.getString("codigo"));
				Unidade uu = new Unidade();
				uu.setId(rs.getInt("id_unidade"));
				uu.setLotacao(rs.getString("lotacao"));

				cc.setUnidade(uu);
				turma.setComponenteCurricular(cc);
				turmas.add(turma);
			}
			st.close();
			rs.close();

			return turmas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Turma findById(Integer id) {

		String sql = "SELECT * FROM turma "
				+ "turma left join componente_curricular on turma.id_componente=componente_curricular.id_componente_curricular"
				+ " left join unidade on componente_curricular.id_unidade=unidade.id_unidade where id_turma = ?";

		try (Connection conn = dataSource.getConnection()) {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			Turma turma = null;

			if (rs.next()) {

				turma = new Turma();
				turma.setId(rs.getInt("id_turma"));
				turma.setAno(rs.getString("ano"));
				turma.setPeriodo(rs.getString("periodo"));
				turma.setNivel(rs.getString("nivel"));
				ComponenteCurricular cc = new ComponenteCurricular();
				cc.setId(rs.getInt("id_componente_curricular"));
				cc.setNomeComponenteCurricular(rs.getString("nome_componente_curricular"));
				cc.setCodigo(rs.getString("codigo"));
				Unidade uu = new Unidade();
				uu.setId(rs.getInt("id_unidade"));
				uu.setLotacao(rs.getString("lotacao"));

				cc.setUnidade(uu);
				turma.setComponenteCurricular(cc);

			}
			ps.close();
			rs.close();

			return turma;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Turma save(Turma u) {
		String sql = "REPLACE INTO TURMA (id_turma, ano, nivel, periodo, id_componente) VALUES (?,?,?,?,?)";

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ps.setString(2, u.getAno());
			ps.setString(3, u.getNivel());
			ps.setString(4, u.getPeriodo());
			ps.setInt(5, u.getComponenteCurricular().getId());
			
			ps.executeUpdate();
			ps.close();
			return u;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Turma u) {
		// TODO Auto-generated method stub

	}

}
