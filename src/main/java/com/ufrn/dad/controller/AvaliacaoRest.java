package com.ufrn.dad.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.dad.model.Avaliacao;
import com.ufrn.dad.repository.AvaliacaoRepository;

@RestController
@RequestMapping("avaliacao")
public class AvaliacaoRest {
	
	@Autowired
	AvaliacaoRepository repository;
	
	@PostMapping
	public Avaliacao save(@Valid @RequestBody Avaliacao avaliacao) {
		return repository.save(avaliacao);
	}
	
	@GetMapping
	public List<Avaliacao> findAll(){
		return repository.findAll();
	}
}
