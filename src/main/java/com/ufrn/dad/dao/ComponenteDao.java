package com.ufrn.dad.dao;

import java.util.List;

import com.ufrn.dad.model.ComponenteCurricular;

public interface ComponenteDao {

	List<ComponenteCurricular> findAll();

	ComponenteCurricular getById(Integer id);

	ComponenteCurricular save(ComponenteCurricular componente);

	ComponenteCurricular update(ComponenteCurricular componente);

	void delete(ComponenteCurricular componente);

}