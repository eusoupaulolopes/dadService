package com.ufrn.dad.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.dad.model.ComponenteCurricular;
import com.ufrn.dad.model.Unidade;
import com.ufrn.dad.repository.ComponenteCurricularRepository;

@RestController
@RequestMapping("componente")
public class ComponenteCurricularRest {
	
	@Autowired
	ComponenteCurricularRepository repository;
	
	
	@PostMapping
	public ComponenteCurricular save(@Valid @RequestBody ComponenteCurricular componente) {
		return repository.save(componente);
	}
	
	/**
	 * Lista todos os Componentes cadastradas
	 * 
	 * @return
	 */
	@GetMapping()
	public List<ComponenteCurricular> findAll() {
		return repository.findAll();
	}
	
	
	
	
	
	
	

}
