package com.ufrn.dad.dao;

import java.util.List;

import com.ufrn.dad.model.ComponenteCurricular;

public interface ComponenteDao {

	/**
	 * Lista todos os componentes
	 * @return
	 */
	List<ComponenteCurricular> findAll();
	/**
	 * Busca um componente especifico na base
	 * @param id id do componente.
	 * @return
	 */
	ComponenteCurricular findById(Integer id);
	/**
	 * Insere ou atualiza um componente na base de dados
	 * @param componente
	 * @return
	 */
	ComponenteCurricular save(ComponenteCurricular componente);

	/**
	 * Remove um componente da base de dados
	 * @param componente
	 */
	void delete(ComponenteCurricular componente);
	/**
	 * Lista todos os componentes com um dado nome
	 * @param nome_componente_curricular nome do componente
	 * @return
	 */
	List<ComponenteCurricular> findAllByNomeComponente(String nome_componente_curricular);

}