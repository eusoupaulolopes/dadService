package com.ufrn.dad.dao;

import java.util.List;

import com.ufrn.dad.model.Avaliacao;

public interface AvaliacaoDao {

	List<Avaliacao> findAll();

	Avaliacao findById(Integer id);

	Avaliacao save(Avaliacao avaliacao);

	void delete(Avaliacao avaliacao);

}