package com.ufrn.dad.dao;

import java.util.List;

import com.ufrn.dad.model.Avaliacao;

public interface AvaliacaoDao {
	/**
	 * Busca todas as avaliacoes no banco.
	 * @return lista de avaliacoes
	 */
	List<Avaliacao> findAll();
	
	/**
	 * Busca uma avaliacao especifica
	 * @param id id da avaliacao
	 * @return
	 */
	Avaliacao findById(Integer id);
/**
 * Salva ou atualiza uma avaliacao na base de dados
 * @param avaliacao
 * @return
 */
	Avaliacao save(Avaliacao avaliacao);

	/**
	 * Remove uma avaliacao da base de dados
	 * @param avaliacao
	 */
	void delete(Avaliacao avaliacao);
	
	/**
	 * Busca todas as avaliacoes por um docente
	 * @param id id do docente 
	 * @return
	 */
	List<Avaliacao> findByDocente(Integer id);
	
	/**
	 * Busca todas as avaliacoes de um dado componente
	 * @param id id do componente
	 * @return
	 */
	List<Avaliacao> findByComponente(Integer id);

}