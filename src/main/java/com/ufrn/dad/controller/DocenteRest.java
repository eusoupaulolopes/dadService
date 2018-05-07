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

import com.ufrn.dad.model.Docente;
import com.ufrn.dad.repository.DocenteRepository;

@RestController
@RequestMapping("docente")
public class DocenteRest {
	
	@Autowired
	DocenteRepository repository;

	/**
	 * Metodo para salvar um componente
	 * 
	 * @param componente
	 * @return
	 */
	@PostMapping
	public Docente save(@Valid @RequestBody Docente componente) {
		return repository.save(componente);
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
	 * Apresenta um Docente pelo Id
	 * 
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Docente> getById(@PathVariable(value = "id") Integer id) {
		Optional<Docente> componente = repository.findById(id);
		
		if (componente == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(componente.get());
	}
	
	/**
	 * Atualiza um docente
	 * @param id
	 * @param docenteInfo
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Docente> update(@PathVariable(value = "id") Integer id, @RequestBody Docente docenteInfo) {
		Optional<Docente> docente = repository.findById(id);
		
		if (docente == null) {
			return ResponseEntity.notFound().build();
		}

		docente.get().setData_admissao(docenteInfo.getData_admissao());
		docente.get().setFormacao(docenteInfo.getFormacao());
		docente.get().setNome(docenteInfo.getNome());
		docente.get().setUnidade(docenteInfo.getUnidade());
		Docente updateDocente = repository.save(docente.get());
		return ResponseEntity.ok(updateDocente);
	}

	
	

}
