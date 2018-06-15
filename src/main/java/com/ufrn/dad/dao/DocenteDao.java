package com.ufrn.dad.dao;

import java.util.List;

import com.ufrn.dad.model.Docente;

public interface DocenteDao {

	List<Docente> findAll();

	Docente findById(Integer id);

	Docente save(Docente docente);

	void delete(Docente docente);

}