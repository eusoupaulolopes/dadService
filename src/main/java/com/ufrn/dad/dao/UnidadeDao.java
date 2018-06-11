package com.ufrn.dad.dao;

import java.util.List;

import com.ufrn.dad.model.Unidade;

public interface UnidadeDao {
	public List<Unidade> findAll();
	public Unidade findById(Integer id);
	public Unidade save(Unidade u);
	public void update(Unidade u);
	public void delete(Unidade u);
}
