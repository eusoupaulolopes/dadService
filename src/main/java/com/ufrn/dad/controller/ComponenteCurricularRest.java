package com.ufrn.dad.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.dad.dao.impl.ComponenteDaoImpl;
import com.ufrn.dad.model.Avaliacao;
import com.ufrn.dad.model.ComponenteCurricular;
import com.ufrn.dad.model.Docente;
import com.ufrn.dad.model.Unidade;
import com.ufrn.dad.repository.ComponenteCurricularRepository;

@RestController
@RequestMapping("componente")
public class ComponenteCurricularRest {

	@Autowired
	ComponenteDaoImpl repository;

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
	
	/**
	 * Lista um Componente pelo ID cadastradas
	 * 
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ComponenteCurricular> getById(@PathVariable(value = "id") Integer id) {
		ComponenteCurricular componente = repository.findById(id);

		if (componente == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(componente);
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<ComponenteCurricular> update(@PathVariable(value = "id") Integer id,
			@RequestBody ComponenteCurricular componenteInfo) {
		ComponenteCurricular componente = repository.findById(id);

		if (componente == null) {
			return ResponseEntity.notFound().build();
		}

		componente.setCodigo(componenteInfo.getCodigo());
		componente.setNomeComponenteCurricular(componenteInfo.getNomeComponenteCurricular());
		componente.setUnidade(componenteInfo.getUnidade());

		ComponenteCurricular updateComponente = repository.save(componente);
		return ResponseEntity.ok(updateComponente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ComponenteCurricular> delete(@PathVariable(value = "id") Integer id) {
		ComponenteCurricular componente = repository.findById(id);
		if (componente == null)
			return ResponseEntity.notFound().build();

		repository.delete(componente);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}/unidade")
	public ResponseEntity<Unidade> getUnidadeByComponente(@PathVariable(value = "id") Integer id){
		ComponenteCurricular componente = repository.findById(id);
		if (componente == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(componente.getUnidade());
	}
	
	/**
	 * Lista todos componentes cadastrados contendo o nome_componente_curricular passado
	 * 
	 * @return
	 */
	@GetMapping(params = { "nome_componente_curricular" })
	public List<ComponenteCurricular> findAllByName(@RequestParam("nome_componente_curricular") String nome_componente_curricular) {
		return repository.findAllByNomeComponente(nome_componente_curricular);
	}

}
