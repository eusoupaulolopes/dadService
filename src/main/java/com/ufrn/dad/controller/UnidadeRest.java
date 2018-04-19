package com.ufrn.dad.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.dad.model.ComponenteCurricular;
import com.ufrn.dad.model.Unidade;
import com.ufrn.dad.repository.UnidadeRepository;

/**
 * Rest Controller para manipulaçao com Unidade de ensino.
 * 
 * @author paulohq
 */

@RestController
@RequestMapping("unidades")
public class UnidadeRest {

	@Autowired
	UnidadeRepository repository;

	/**
	 * Metodo para salvar uma unidade
	 * 
	 * @param unidade
	 * @return
	 */
	@PostMapping
	public Unidade save(@Valid @RequestBody Unidade unidade) {
		return repository.save(unidade);
	}

	/**
	 * Lista todas as unidades cadastradas
	 * 
	 * @return
	 */
	@GetMapping()
	public List<Unidade> findAll() {
		return repository.findAll();
	}

	/**
	 * Apresenta uma Unidade pelo Id
	 * 
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Unidade> getById(@PathVariable(value = "id") Integer id) {
		Optional<Unidade> unidade = repository.findById(id);
		
		if (unidade == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(unidade.get());
	}
	
	/**
	 * Apresenta os componentes de uma unidade pelo Id da unidade
	 * 
	 * @return
	 */
	@GetMapping("/{id}/componentes")
	public List<ComponenteCurricular> findAllComponentes(@PathVariable(value = "id") Integer id) {
		Optional<Unidade> unidade = repository.findById(id);
		
		if (unidade == null) {
			List<ComponenteCurricular> empty = new ArrayList<ComponenteCurricular>();
			return empty;
		}
		
		return unidade.get().getComponentes();
	}
	

	/**
	 * Atualiza uma unidade
	 * @param id
	 * @param unidadeInfo
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Unidade> update(@PathVariable(value = "id") Integer id, @RequestBody Unidade unidadeInfo) {
		Optional<Unidade> unidade = repository.findById(id);
		
		if (unidade == null) {
			return ResponseEntity.notFound().build();
		}

		unidade.get().setNome(unidadeInfo.getNome());
		Unidade updateUnidade = repository.save(unidade.get());
		return ResponseEntity.ok(updateUnidade);
	}

	/**
	 * Deleta uma unidade
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Unidade> delete(@PathVariable(value = "id") Integer id) {
		Optional<Unidade> unidade = repository.findById(id);
		if (unidade == null)
			return ResponseEntity.notFound().build();

		repository.delete(unidade.get());
		return ResponseEntity.ok().build();
	}

}
