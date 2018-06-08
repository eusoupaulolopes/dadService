package com.ufrn.dad.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	

	@PutMapping("/{id}")
	public ResponseEntity<Avaliacao> update(@PathVariable(value = "id") Integer id,
			@RequestBody Avaliacao avaliacaoInfo) {
		Optional<Avaliacao> avaliacao = repository.findById(id);

		if (avaliacao == null) {
			return ResponseEntity.notFound().build();
		}
		
		if(avaliacaoInfo.getId() != null){
			avaliacao.get().setId(avaliacaoInfo.getId() );
		}
		if(avaliacaoInfo.getDocente() != null){
			avaliacao.get().setDocente(avaliacaoInfo.getDocente());
		}
		
		if(avaliacaoInfo.getTurma() != null)
			avaliacao.get().setTurma(avaliacaoInfo.getTurma());
		
		if(avaliacaoInfo.getQtdDiscentes() != null)
			avaliacao.get().setQtdDiscentes(avaliacaoInfo.getQtdDiscentes());
		
		
		if(avaliacaoInfo.getPosturaProfissional() != null)
			avaliacao.get().setPosturaProfissional(avaliacaoInfo.getPosturaProfissional());
		
		if (avaliacaoInfo.getPosturaProfissionalDP() != null)
			avaliacao.get().setPosturaProfissionalDP(avaliacaoInfo.getPosturaProfissionalDP());
		
		if(avaliacaoInfo.getAtuacaoProfissional() != null)
			avaliacao.get().setAtuacaoProfissional(avaliacaoInfo.getAtuacaoProfissionalDP());
		
		if(avaliacaoInfo.getAtuacaoProfissionalDP() != null)
			avaliacao.get().setAtuacaoProfissionalDP(avaliacaoInfo.getAtuacaoProfissionalDP());
		
		Avaliacao updateAvaliacao = repository.save(avaliacao.get());
		return ResponseEntity.ok(updateAvaliacao);
	}
	
}
