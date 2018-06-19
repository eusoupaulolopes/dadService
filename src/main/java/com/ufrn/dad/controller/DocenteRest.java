package com.ufrn.dad.controller;

import java.util.List;

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

import com.ufrn.dad.dao.dto.DocenteMediasDTO;
import com.ufrn.dad.dao.impl.DocenteDaoImpl;
import com.ufrn.dad.model.Docente;

@RestController
@RequestMapping("docente")
public class DocenteRest {

	@Autowired
	DocenteDaoImpl repository;

	/**
	 * Metodo para salvar um docente
	 * 
	 * @param componente
	 * @return
	 */
	@PostMapping
	public Docente save(@Valid @RequestBody Docente docente) {
		return repository.save(docente);
	}

	/**
	 * Lista todos docentes cadastradas
	 * 
	 * @return
	 */
	@GetMapping()
	public List<Docente> findAll() {
		return repository.findAll();
	}

	/**
	 * Lista todos docentes cadastradas contendo o nome passado
	 * 
	 * @return
	 */
	@GetMapping(params = { "nome" })
	public List<Docente> findAllByName(@RequestParam("nome") String nome) {
		return repository.findAllByNome(nome);
	}

	/**
	 * Apresenta um Docente pelo Id
	 * 
	 * @return
	 */
	@GetMapping("/{id}/media")
	public ResponseEntity<DocenteMediasDTO> getMediasById(@PathVariable(value = "id") Integer id) {
		DocenteMediasDTO dto = repository.findMediasByID(id);

		if (dto == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(dto);
	}

	/*
	 * Apresenta um Docente pelo Id
	 * 
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Docente> getById(@PathVariable(value = "id") Integer id) {
		Docente docente = repository.findById(id);

		if (docente == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(docente);
	}

	/**
	 * Atualiza um docente
	 * 
	 * @param id
	 * @param docenteInfo
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Docente> update(@PathVariable(value = "id") Integer id, @RequestBody Docente docenteInfo) {
		Docente docente = repository.findById(id);

		if (docente == null) {
			return ResponseEntity.notFound().build();
		}

		docente.setData_admissao(docenteInfo.getData_admissao());
		docente.setFormacao(docenteInfo.getFormacao());
		docente.setNome(docenteInfo.getNome());
		docente.setUnidade(docenteInfo.getUnidade());
		Docente updateDocente = repository.save(docente);
		return ResponseEntity.ok(updateDocente);
	}

}
