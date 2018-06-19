package com.ufrn.dad.dao;

import java.util.List;

import com.ufrn.dad.model.Unidade;

public interface UnidadeDao {
	
	/**
	 * Lista todas as unidades na base de dados
	 * @return
	 */
	public List<Unidade> findAll();

	/**
	 * Busca uma unidade na base de dados
	 * @param id id da unidade
	 * @return
	 */
	public Unidade findById(Integer id);

	/**
	 * Insere ou atualiza uma unidade na base de dados
	 * @param u
	 * @return
	 */
	public Unidade save(Unidade u);

	/**
	 * Remove uma unidade da base de dados.
	 * @see Cascade
	 * IMPORTANTE: Caso uma unidade seja removida todos os dados relevantes a ela sera removido em cascata
	 * @param u
	 */
	public void delete(Unidade u);
}
