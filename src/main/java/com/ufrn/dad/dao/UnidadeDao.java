package com.ufrn.dad.dao;

import java.util.List;

import com.ufrn.dad.model.Unidade;

public interface UnidadeDao {
	public List<Unidade> getAll();
	public Unidade getById(Integer id);
	public void save(Unidade u);
	public void update(Unidade u);
	public void delete(Unidade u);
}
