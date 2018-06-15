package com.ufrn.dad.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.dad.dao.AvaliacaoDao;
import com.ufrn.dad.dao.GenericDao;
import com.ufrn.dad.model.Avaliacao;
import com.ufrn.dad.model.ComponenteCurricular;
import com.ufrn.dad.model.Docente;
import com.ufrn.dad.model.Turma;
import com.ufrn.dad.model.Unidade;

public class AvaliacaoDaoImpl extends GenericDao implements AvaliacaoDao {

	/* (non-Javadoc)
	 * @see com.ufrn.dad.dao.impl.AvaliacaoDao#getAll()
	 */
	@Override
	public List<Avaliacao> getAll() {
		String sql = "";

		List<Avaliacao> avaliacoes = new ArrayList<>();

		try(Connection conn = dataSource.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setId(rs.getInt("id_avaliacao"));
				avaliacao.setAprovados(rs.getDouble("aprovados"));
				avaliacao.setAtuacaoProfissional(rs.getDouble("atuacao_profissional"));
				avaliacao.setAtuacaoProfissionalDP(rs.getDouble("atuacao_profissionaldp"));
				avaliacao.setMediaAprovados(rs.getDouble("media_aprovados"));
				avaliacao.setPosturaProfissional(rs.getDouble("postura_profissional"));
				avaliacao.setPosturaProfissionalDP(rs.getDouble("postura_profissionaldp"));
				avaliacao.setQtdDiscentes(rs.getInt("qtd_discentes"));

				Docente docente = new Docente();
				docente.setId(rs.getInt("id_docente"));
				docente.setNome(rs.getString("nome"));
				docente.setData_admissao(rs.getDate("data_admissao"));
				docente.setFormacao(rs.getString("formacao"));
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
				componente.setNomeComponenteCurricular(rs.getString("nome"));
				turma.setComponenteCurricular(componente);

				Unidade unidade = new Unidade();
				unidade.setId(rs.getInt("id_unidade"));
				unidade.setLotacao(rs.getString("lotacao"));
				componente.setUnidade(unidade);
			}
			ps.close();
			rs.close();

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avaliacoes;
	}

	/* (non-Javadoc)
	 * @see com.ufrn.dad.dao.impl.AvaliacaoDao#getById(java.lang.Integer)
	 */
	@Override
	public Avaliacao getById(Integer id) {
		String sql = "";
		Avaliacao avaliacao = null;

		try(Connection conn = dataSource.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			avaliacao = new Avaliacao();
			avaliacao.setId(rs.getInt("id_avaliacao"));
			avaliacao.setAprovados(rs.getDouble("aprovados"));
			avaliacao.setAtuacaoProfissional(rs.getDouble("atuacao_profissional"));
			avaliacao.setAtuacaoProfissionalDP(rs.getDouble("atuacao_profissionaldp"));
			avaliacao.setMediaAprovados(rs.getDouble("media_aprovados"));
			avaliacao.setPosturaProfissional(rs.getDouble("postura_profissional"));
			avaliacao.setPosturaProfissionalDP(rs.getDouble("postura_profissionaldp"));
			avaliacao.setQtdDiscentes(rs.getInt("qtd_discentes"));

			Docente docente = new Docente();
			docente.setId(rs.getInt("id_docente"));
			docente.setNome(rs.getString("nome"));
			docente.setData_admissao(rs.getDate("data_admissao"));
			docente.setFormacao(rs.getString("formacao"));
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
			componente.setNomeComponenteCurricular(rs.getString("nome"));
			turma.setComponenteCurricular(componente);

			Unidade unidade = new Unidade();
			unidade.setId(rs.getInt("id_unidade"));
			unidade.setLotacao(rs.getString("lotacao"));
			componente.setUnidade(unidade);
			
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avaliacao;
	}

	/* (non-Javadoc)
	 * @see com.ufrn.dad.dao.impl.AvaliacaoDao#save(com.ufrn.dad.model.Avaliacao)
	 */
	@Override
	public Avaliacao save(Avaliacao avaliacao) {
		String sql = "INSERT INTO avaliacao "
				+ "(aprovados, atuacao_profissional, atuacao_profissionaldp,"
				+ "media_aprovados, postura_profissional, postura_profissionaldp,"
				+ "qtd_discentes, id_docente, id_turma)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try(Connection conn = dataSource.getConnection()){
			try(PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setDouble(1, avaliacao.getAprovados());
				ps.setDouble(2, avaliacao.getAtuacaoProfissional());
				ps.setDouble(3, avaliacao.getAtuacaoProfissionalDP());
				ps.setDouble(4, avaliacao.getMediaAprovados());
				ps.setDouble(5, avaliacao.getPosturaProfissional());
				ps.setDouble(6, avaliacao.getPosturaProfissionalDP());
				ps.setInt(7, avaliacao.getQtdDiscentes());
				ps.setInt(8, avaliacao.getTurma().getId());

				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return avaliacao;

	}

	/* (non-Javadoc)
	 * @see com.ufrn.dad.dao.impl.AvaliacaoDao#update(com.ufrn.dad.model.Avaliacao)
	 */
	@Override
	public Avaliacao update(Avaliacao avaliacao) {
		String sql = "UPDATE avaliacao SET "
				+ "aprovados=?, atuacao_profissional=?, atuacao_profissionaldp=?,"
				+ "media_aprovados=?, postura_profissional=?, postura_profissionaldp=?,"
				+ "qtd_discentes=?, id_docente=?, id_turma=?"
				+ "WHERE id_avaliacao=?";

		try(Connection conn = dataSource.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
				ps.setDouble(1, avaliacao.getAprovados());
				ps.setDouble(2, avaliacao.getAtuacaoProfissional());
				ps.setDouble(3, avaliacao.getAtuacaoProfissionalDP());
				ps.setDouble(4, avaliacao.getMediaAprovados());
				ps.setDouble(5, avaliacao.getPosturaProfissional());
				ps.setDouble(6, avaliacao.getPosturaProfissionalDP());
				ps.setInt(7, avaliacao.getQtdDiscentes());
				ps.setInt(8, avaliacao.getTurma().getId());

				ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return avaliacao;

	}

	/* (non-Javadoc)
	 * @see com.ufrn.dad.dao.impl.AvaliacaoDao#delete(com.ufrn.dad.model.Avaliacao)
	 */
	@Override
	public void delete(Avaliacao avaliacao) {
		String sql = "DELETE FROM avaliacao WHERE id_avaliacao=?";
		try(Connection conn = dataSource.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, avaliacao.getId());
				ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
