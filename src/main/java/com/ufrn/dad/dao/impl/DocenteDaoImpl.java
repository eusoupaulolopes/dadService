package com.ufrn.dad.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ufrn.dad.dao.DocenteDao;
import com.ufrn.dad.dao.GenericDao;
import com.ufrn.dad.model.Docente;
import com.ufrn.dad.model.Turma;
import com.ufrn.dad.model.Unidade;

@Repository
public class DocenteDaoImpl extends GenericDao implements DocenteDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ufrn.dad.dao.impl.DocenteDao#getAll()
	 */
	@Override
	public List<Docente> findAll() {
		String sql = "SELECT * FROM docente docente left join unidade on docente.id_unidade=unidade.id_unidade";
		try (Connection conn = dataSource.getConnection()) {
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				List<Docente> docentes = new ArrayList<Docente>();
				while (rs.next()) {
					Docente d = new Docente();

					d.setId(rs.getInt("id_docente"));
					d.setFormacao(rs.getString("formacao"));
					d.setNome(rs.getString("nome"));
					d.setData_admissao(rs.getDate("data_admissao"));

					Unidade u = new Unidade();
					u.setId(rs.getInt("id_unidade"));
					u.setLotacao(rs.getString("lotacao"));
					d.setUnidade(u);
					docentes.add(d);
				}
				return docentes;
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Docente findById(Integer id) {
		String sql = "SELECT * FROM docente "
				+ "docente left join unidade on docente.id_unidade=unidade.id_unidade where id_docente = ?";

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			Docente docente = null;
			if (rs.next()) {
				docente = new Docente();
				docente.setId(rs.getInt("id_docente"));
				
				Date date = rs.getDate("data_admissao");
				docente.setData_admissao(date);
				docente.setFormacao(rs.getString("formacao"));
				docente.setNome(rs.getString("nome"));
				Unidade unidade = new Unidade();
				unidade.setId(rs.getInt("id_unidade"));
				unidade.setLotacao(rs.getString("lotacao"));
				docente.setUnidade(unidade);

			}
			rs.close();
			ps.close();
			return docente;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Docente save(Docente docente) {
		String sql = "INSERT INTO docente (id_docente, data_admissao, formacao, nome, id_unidade) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, docente.getId());
			ps.setDate(2, new java.sql.Date(docente.getData_admissao().getTime()));
			ps.setString(3, docente.getFormacao());
			ps.setString(4, docente.getNome());
			ps.setInt(5, docente.getUnidade().getId());

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return docente;

	}

	// */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ufrn.dad.dao.impl.DocenteDao#delete(com.ufrn.dad.model.Docente)
	 */
	@Override
	public void delete(Docente docente) {
		String sql = "DELETE FROM docente WHERE id_docente=?";
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, docente.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public List<Docente> findAllByNome(String nome) {
		String sql = "SELECT * FROM docente "
				+ "docente left join unidade on docente.id_unidade=unidade.id_unidade where nome LIKE ?";

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+nome+"%");

			ResultSet rs = ps.executeQuery();
			List<Docente> docentes = new ArrayList<Docente>();
			 while(rs.next()) {
				 Docente docente = new Docente();
				docente.setId(rs.getInt("id_docente"));
				Date date = rs.getDate("data_admissao");
				docente.setData_admissao(date);
				docente.setFormacao(rs.getString("formacao"));
				docente.setNome(rs.getString("nome"));
				Unidade unidade = new Unidade();
				unidade.setId(rs.getInt("id_unidade"));
				unidade.setLotacao(rs.getString("lotacao"));
				docente.setUnidade(unidade);
				docentes.add(docente);

			}
			rs.close();
			return docentes;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

}
