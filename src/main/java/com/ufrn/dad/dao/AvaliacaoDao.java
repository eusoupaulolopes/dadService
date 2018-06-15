package com.ufrn.dad.dao;

import java.util.List;

import com.ufrn.dad.model.Avaliacao;

public interface AvaliacaoDao {

	List<Avaliacao> getAll();

	Avaliacao getById(Integer id);

	Avaliacao save(Avaliacao avaliacao);

	Avaliacao update(Avaliacao avaliacao);

	void delete(Avaliacao avaliacao);

}