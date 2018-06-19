package com.ufrn.dad.dao;

import java.util.List;

import com.ufrn.dad.dao.dto.DocenteMediasDTO;
import com.ufrn.dad.model.Docente;

public interface DocenteDao {
	/**
	 * Lista todos os docentes na base de dados
	 * 
	 * @return
	 */
	List<Docente> findAll();

	/**
	 * Busca um docente 
	 * @param id id do docente
	 * @return
	 */
	Docente findById(Integer id);

	/**
	 * Insere ou atualiza um docente na base de dados
	 * @param docente 
	 * @return
	 */
	Docente save(Docente docente);

	/**
	 * Remove um docente na base de dados
	 * @param docente
	 */
	void delete(Docente docente);

	List<Docente> findAllByNome(String nome);

	DocenteMediasDTO findMediasByID(Integer id);

}