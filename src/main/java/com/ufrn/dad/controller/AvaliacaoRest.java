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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.dad.dao.impl.AvaliacaoDaoImpl;
import com.ufrn.dad.model.Avaliacao;
import com.ufrn.dad.model.Turma;

@RestController
@RequestMapping("avaliacao")
public class AvaliacaoRest {

	@Autowired
	AvaliacaoDaoImpl repository;

	@PostMapping
	public Avaliacao save(@Valid @RequestBody Avaliacao avaliacao) {
		return repository.save(avaliacao);
	}

	@GetMapping
	public List<Avaliacao> findAll() {
		return repository.findAll();
	}

	@GetMapping(params = {"id_docente"})
	public List<Avaliacao> findByDocente(@RequestParam(value = "id_docente") Integer id_docente) {
		return repository.findByDocente(id_docente);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Avaliacao> getById(@PathVariable(value = "id") Integer id) {
		Avaliacao avaliacao = repository.findById(id);

		if (avaliacao == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(avaliacao);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Avaliacao> update(@PathVariable(value = "id") Integer id,
			@RequestBody Avaliacao avaliacaoInfo) {
		Avaliacao avaliacao = repository.findById(id);

		if (avaliacao == null) {
			return ResponseEntity.notFound().build();
		}

		if (avaliacaoInfo.getId() != null) {
			avaliacao.setId(avaliacaoInfo.getId());
		}
		if (avaliacaoInfo.getDocente() != null) {
			avaliacao.setDocente(avaliacaoInfo.getDocente());
		}

		if (avaliacaoInfo.getTurma() != null)
			avaliacao.setTurma(avaliacaoInfo.getTurma());

		if (avaliacaoInfo.getQtdDiscentes() != null)
			avaliacao.setQtdDiscentes(avaliacaoInfo.getQtdDiscentes());

		if (avaliacaoInfo.getPosturaProfissional() != null)
			avaliacao.setPosturaProfissional(avaliacaoInfo.getPosturaProfissional());

		if (avaliacaoInfo.getPosturaProfissionalDP() != null)
			avaliacao.setPosturaProfissionalDP(avaliacaoInfo.getPosturaProfissionalDP());

		if (avaliacaoInfo.getAtuacaoProfissional() != null)
			avaliacao.setAtuacaoProfissional(avaliacaoInfo.getAtuacaoProfissionalDP());

		if (avaliacaoInfo.getAtuacaoProfissionalDP() != null)
			avaliacao.setAtuacaoProfissionalDP(avaliacaoInfo.getAtuacaoProfissionalDP());

		if (avaliacaoInfo.getMediaAprovados() != null)
			avaliacao.setMediaAprovados(avaliacaoInfo.getMediaAprovados());

		if (avaliacaoInfo.getAprovados() != null)
			avaliacao.setAprovados(avaliacaoInfo.getAprovados());

		Avaliacao updateAvaliacao = repository.save(avaliacao);
		return ResponseEntity.ok(updateAvaliacao);
	}

}
