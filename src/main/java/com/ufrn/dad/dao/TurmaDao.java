package com.ufrn.dad.dao;

import java.util.List;

import com.ufrn.dad.model.Turma;

public interface TurmaDao {

	
	public List<Turma> findAll();

	public Turma findById(Integer id);

	public Turma save(Turma u);

	public void delete(Turma u);
}
