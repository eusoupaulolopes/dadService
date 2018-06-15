package com.ufrn.dad.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrn.dad.dao.ComponenteDao;
import com.ufrn.dad.dao.GenericDao;
import com.ufrn.dad.model.ComponenteCurricular;
import com.ufrn.dad.model.Unidade;

public class ComponenteDaoImpl extends GenericDao implements ComponenteDao{

	/* (non-Javadoc)
	 * @see com.ufrn.dad.dao.impl.ComponenteDao#findAll()
	 */
	@Override
	public List<ComponenteCurricular> findAll() {
		String sql = "";
		
		List<ComponenteCurricular> componentes = new ArrayList<>();
		
		try(Connection conn = dataSource.getConnection()){
			try(PreparedStatement ps = conn.prepareStatement(sql)){
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return componentes;
	}

	/* (non-Javadoc)
	 * @see com.ufrn.dad.dao.impl.ComponenteDao#getById(java.lang.Integer)
	 */
	@Override
	public ComponenteCurricular getById(Integer id) {
		String sql = "";
		ComponenteCurricular componente = null;
		try(Connection conn = dataSource.getConnection()){
			try(PreparedStatement ps = conn.prepareStatement(sql)){
				
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return componente;
	}

	/* (non-Javadoc)
	 * @see com.ufrn.dad.dao.impl.ComponenteDao#save(com.ufrn.dad.model.ComponenteCurricular)
	 */
	@Override
	public ComponenteCurricular save(ComponenteCurricular componente) {
		String sql = "INSERT INTO componente_curricular "
				+ "(id_componente_curricular, codigo, nome_componente_curricular, id_unidade)"
				+ "VALUES (?, ?, ?, ?)";
		
		try(Connection conn = dataSource.getConnection()){
			try(PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setInt(1, componente.getId());
				ps.setString(2, componente.getCodigo());
				ps.setString(3, componente.getNomeComponenteCurricular());
				ps.setInt(4, componente.getUnidade().getId());
				
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return componente;
		
	}

	/* (non-Javadoc)
	 * @see com.ufrn.dad.dao.impl.ComponenteDao#update(com.ufrn.dad.model.ComponenteCurricular)
	 */
	@Override
	public ComponenteCurricular update(ComponenteCurricular componente) {
		String sql = "UPDATE componente_curricular SET "
				+ "codigo=?, nome_componente_curricular=?, id_unidade=? "
				+ "WHERE id_componente_curricular=?";
		
		try(Connection conn = dataSource.getConnection()){
			try(PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setString(1, componente.getCodigo());
				ps.setString(2, componente.getNomeComponenteCurricular());
				ps.setInt(3, componente.getUnidade().getId());
				ps.setInt(3, componente.getId());
				
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return componente;
		
	}

	/* (non-Javadoc)
	 * @see com.ufrn.dad.dao.impl.ComponenteDao#delete(com.ufrn.dad.model.ComponenteCurricular)
	 */
	@Override
	public void delete(ComponenteCurricular componente) {
		String sql = "DELETE FROM componente_curricular WHERE id_componente_curricular=?";
		try(Connection conn = dataSource.getConnection()){
			try(PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setInt(1, componente.getId());
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

}
