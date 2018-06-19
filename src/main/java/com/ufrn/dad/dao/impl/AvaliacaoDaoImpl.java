package com.ufrn.dad.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ufrn.dad.dao.AvaliacaoDao;
import com.ufrn.dad.dao.GenericDao;
import com.ufrn.dad.model.Avaliacao;
import com.ufrn.dad.model.ComponenteCurricular;
import com.ufrn.dad.model.Docente;
import com.ufrn.dad.model.Turma;
import com.ufrn.dad.model.Unidade;

@Repository
public class AvaliacaoDaoImpl extends GenericDao implements AvaliacaoDao {

	@Override
	public List<Avaliacao> findAll() {
		String sql = "SELECT * FROM avaliacao " + "join docente on avaliacao.id_docente=docente.id_docente "
				+ "join turma on avaliacao.id_turma=turma.id_turma "
				+ "join componente_curricular on turma.id_componente=componente_curricular.id_componente_curricular "
				+ "join unidade on unidade.id_unidade=componente_curricular.id_unidade ";

		List<Avaliacao> avaliacoes = new ArrayList<>();

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setId(rs.getInt("id_avaliacao"));
				avaliacao.setAprovados(rs.getDouble("aprovados"));
				avaliacao.setAtuacaoProfissional(rs.getDouble("atuacao_profissional"));
				avaliacao.setAtuacaoProfissionalDP(rs.getDouble("atuacao_profissionaldp"));
				avaliacao.setMediaAprovados(rs.getDouble("media_aprovados"));
				avaliacao.setPosturaProfissional(rs.getDouble("postura_profissional"));
				avaliacao.setPosturaProfissionalDP(rs.getDouble("postura_profissionaldp"));
				avaliacao.setQtdDiscentes(rs.getInt("qtd_discentes"));

				Unidade unidade = new Unidade();
				unidade.setId(rs.getInt("id_unidade"));
				unidade.setLotacao(rs.getString("lotacao"));

				Docente docente = new Docente();
				docente.setId(rs.getInt("id_docente"));
				docente.setNome(rs.getString("nome"));
				docente.setData_admissao(rs.getDate("data_admissao"));
				docente.setFormacao(rs.getString("formacao"));
				docente.setUnidade(unidade);
				avaliacao.setDocente(docente);

				Turma turma = new Turma();
				turma.setId(rs.getInt("id_turma"));
				turma.setAno(rs.getString("ano"));
				turma.setPeriodo(rs.getString("periodo"));
				turma.setNivel(rs.getString("nivel"));
				avaliacao.setTurma(turma);

				ComponenteCurricular componente = new ComponenteCurricular();
				componente.setId(rs.getInt("id_componente_curricular"));
				componente.setCodigo(rs.getString("codigo"));
				componente.setNomeComponenteCurricular(rs.getString("nome_componente_curricular"));
				turma.setComponenteCurricular(componente);

				componente.setUnidade(unidade);
				avaliacoes.add(avaliacao);
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avaliacoes;
	}

	@Override
	public Avaliacao findById(Integer id) {
		String sql = "SELECT * FROM avaliacao " + "join docente on avaliacao.id_docente=docente.id_docente "
				+ "join turma on avaliacao.id_turma=turma.id_turma "
				+ "join componente_curricular on turma.id_componente=componente_curricular.id_componente_curricular "
				+ "join unidade on unidade.id_unidade=componente_curricular.id_unidade " + "WHERE id_avaliacao = ?";

		Avaliacao avaliacao = null;

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				avaliacao = new Avaliacao();
				avaliacao.setId(rs.getInt("id_avaliacao"));
				avaliacao.setAprovados(rs.getDouble("aprovados"));
				avaliacao.setAtuacaoProfissional(rs.getDouble("atuacao_profissional"));
				avaliacao.setAtuacaoProfissionalDP(rs.getDouble("atuacao_profissionaldp"));
				avaliacao.setMediaAprovados(rs.getDouble("media_aprovados"));
				avaliacao.setPosturaProfissional(rs.getDouble("postura_profissional"));
				avaliacao.setPosturaProfissionalDP(rs.getDouble("postura_profissionaldp"));
				avaliacao.setQtdDiscentes(rs.getInt("qtd_discentes"));

				Unidade unidade = new Unidade();
				unidade.setId(rs.getInt("id_unidade"));
				unidade.setLotacao(rs.getString("lotacao"));

				Docente docente = new Docente();
				docente.setId(rs.getInt("id_docente"));
				docente.setNome(rs.getString("nome"));
				docente.setData_admissao(rs.getDate("data_admissao"));
				docente.setFormacao(rs.getString("formacao"));
				docente.setUnidade(unidade);
				avaliacao.setDocente(docente);

				Turma turma = new Turma();
				turma.setId(rs.getInt("id_turma"));
				turma.setAno(rs.getString("ano"));
				turma.setPeriodo(rs.getString("periodo"));
				turma.setNivel(rs.getString("nivel"));
				avaliacao.setTurma(turma);

				ComponenteCurricular componente = new ComponenteCurricular();
				componente.setId(rs.getInt("id_componente_curricular"));
				componente.setCodigo(rs.getString("codigo"));
				componente.setNomeComponenteCurricular(rs.getString("nome_componente_curricular"));
				turma.setComponenteCurricular(componente);

				componente.setUnidade(unidade);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avaliacao;

	}

	@Override
	public Avaliacao save(Avaliacao avaliacao) {
		String sql = "REPLACE INTO avaliacao " + "(aprovados, atuacao_profissional, atuacao_profissionaldp,"
				+ "media_aprovados, postura_profissional, postura_profissionaldp,"
				+ "qtd_discentes, id_docente, id_turma)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setDouble(1, avaliacao.getAprovados());
				ps.setDouble(2, avaliacao.getAtuacaoProfissional());
				ps.setDouble(3, avaliacao.getAtuacaoProfissionalDP());
				ps.setDouble(4, avaliacao.getMediaAprovados());
				ps.setDouble(5, avaliacao.getPosturaProfissional());
				ps.setDouble(6, avaliacao.getPosturaProfissionalDP());
				ps.setInt(7, avaliacao.getQtdDiscentes());
				ps.setInt(8, avaliacao.getDocente().getId());
				ps.setInt(9, avaliacao.getTurma().getId());
				
				ps.executeUpdate();
				
//				System.out.println(ps.toString()); Caso queiramos mostrar uma consulta
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return avaliacao;

	}

	@Override
	public void delete(Avaliacao avaliacao) {
		String sql = "DELETE FROM avaliacao WHERE id_avaliacao=?";
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, avaliacao.getId());
			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public List<Avaliacao> findByDocente(Integer id) {

		String sql = "SELECT * FROM avaliacao " + "join docente on avaliacao.id_docente=docente.id_docente "
				+ "join turma on avaliacao.id_turma=turma.id_turma "
				+ "join componente_curricular on turma.id_componente=componente_curricular.id_componente_curricular "
				+ "join unidade on unidade.id_unidade=componente_curricular.id_unidade "
				+ "where avaliacao.id_docente = ?";

		List<Avaliacao> avaliacoes = new ArrayList<>();

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setId(rs.getInt("id_avaliacao"));
				avaliacao.setAprovados(rs.getDouble("aprovados"));
				avaliacao.setAtuacaoProfissional(rs.getDouble("atuacao_profissional"));
				avaliacao.setAtuacaoProfissionalDP(rs.getDouble("atuacao_profissionaldp"));
				avaliacao.setMediaAprovados(rs.getDouble("media_aprovados"));
				avaliacao.setPosturaProfissional(rs.getDouble("postura_profissional"));
				avaliacao.setPosturaProfissionalDP(rs.getDouble("postura_profissionaldp"));
				avaliacao.setQtdDiscentes(rs.getInt("qtd_discentes"));

				Unidade unidade = new Unidade();
				unidade.setId(rs.getInt("id_unidade"));
				unidade.setLotacao(rs.getString("lotacao"));

				Docente docente = new Docente();
				docente.setId(rs.getInt("id_docente"));
				docente.setNome(rs.getString("nome"));
				docente.setData_admissao(rs.getDate("data_admissao"));
				docente.setFormacao(rs.getString("formacao"));
				docente.setUnidade(unidade);
				avaliacao.setDocente(docente);

				Turma turma = new Turma();
				turma.setId(rs.getInt("id_turma"));
				turma.setAno(rs.getString("ano"));
				turma.setPeriodo(rs.getString("periodo"));
				turma.setNivel(rs.getString("nivel"));
				avaliacao.setTurma(turma);

				ComponenteCurricular componente = new ComponenteCurricular();
				componente.setId(rs.getInt("id_componente_curricular"));
				componente.setCodigo(rs.getString("codigo"));
				componente.setNomeComponenteCurricular(rs.getString("nome_componente_curricular"));
				turma.setComponenteCurricular(componente);

				componente.setUnidade(unidade);
				avaliacoes.add(avaliacao);
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avaliacoes;
	}

	@Override
	public List<Avaliacao> findByComponente(Integer id) {

		String sql = "SELECT * FROM avaliacao " + "JOIN turma ON avaliacao.id_turma=turma.id_turma "
				+ "JOIN componente_curricular ON turma.id_componente=componente_curricular.id_componente_curricular "
				+ "JOIN docente ON avaliacao.id_docente=docente.id_docente" + "WHERE id_componente_curricular= ?";

		List<Avaliacao> avaliacoes = new ArrayList<>();

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setId(rs.getInt("id_avaliacao"));
				avaliacao.setAprovados(rs.getDouble("aprovados"));
				avaliacao.setAtuacaoProfissional(rs.getDouble("atuacao_profissional"));
				avaliacao.setAtuacaoProfissionalDP(rs.getDouble("atuacao_profissionaldp"));
				avaliacao.setMediaAprovados(rs.getDouble("media_aprovados"));
				avaliacao.setPosturaProfissional(rs.getDouble("postura_profissional"));
				avaliacao.setPosturaProfissionalDP(rs.getDouble("postura_profissionaldp"));
				avaliacao.setQtdDiscentes(rs.getInt("qtd_discentes"));

				Unidade unidade = new Unidade();
				unidade.setId(rs.getInt("id_unidade"));
				unidade.setLotacao(rs.getString("lotacao"));

				Docente docente = new Docente();
				docente.setId(rs.getInt("id_docente"));
				docente.setNome(rs.getString("nome"));
				docente.setData_admissao(rs.getDate("data_admissao"));
				docente.setFormacao(rs.getString("formacao"));
				docente.setUnidade(unidade);
				avaliacao.setDocente(docente);

				Turma turma = new Turma();
				turma.setId(rs.getInt("id_turma"));
				turma.setAno(rs.getString("ano"));
				turma.setPeriodo(rs.getString("periodo"));
				turma.setNivel(rs.getString("nivel"));
				avaliacao.setTurma(turma);

				ComponenteCurricular componente = new ComponenteCurricular();
				componente.setId(rs.getInt("id_componente_curricular"));
				componente.setCodigo(rs.getString("codigo"));
				componente.setNomeComponenteCurricular(rs.getString("nome_componente_curricular"));
				turma.setComponenteCurricular(componente);

				componente.setUnidade(unidade);
				avaliacoes.add(avaliacao);
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avaliacoes;
	}

}
