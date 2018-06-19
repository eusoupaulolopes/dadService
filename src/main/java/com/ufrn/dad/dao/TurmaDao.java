package com.ufrn.dad.dao;

import java.util.List;

import com.ufrn.dad.model.Turma;

public interface TurmaDao {
	
	/**
	 * Lista todas as turmas na base de dados
	 * @return
	 */
	List<Turma> findAll();

	/**
	 * Busca uma turma especifica na base
	 * @param id
	 * @return
	 */
	Turma findById(Integer id);
	
	/**
	 * Insere ou atualiza uma turma na base de dados
	 * @param u
	 * @return
	 */
	Turma save(Turma u);

	/**
	 * Remove uma turma da base de dados
	 * @param u
	 */
	void delete(Turma u);
}
