package com.ufrn.dad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.dad.model.ComponenteCurricular;

public interface ComponenteCurricularRepository extends JpaRepository<ComponenteCurricular, Integer> {
	
	/**
	 * Retorna um componente buscado pelo nome.
	 * @param nome
	 */
	ComponenteCurricular findByNomeComponenteCurricular(String nome);
}
