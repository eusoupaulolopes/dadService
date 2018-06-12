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
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.dad.dao.impl.TurmaDaoImpl;
import com.ufrn.dad.model.Turma;
import com.ufrn.dad.model.Unidade;

@RestController
@RequestMapping("turma")
public class TurmaRest {

	@Autowired
	public TurmaDaoImpl repository;

	@GetMapping()
	public List<Turma> findAll() {
		return repository.findAll();
	}

	/**
	 * Apresenta uma Turma pelo Id
	 * 
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Turma> getById(@PathVariable(value = "id") Integer id) {
		Turma turma = repository.findById(id);

		if (turma == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(turma);
	}

	@PostMapping
	public Turma save(@Valid @RequestBody Turma turma) {
		return repository.save(turma);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Turma> update(@PathVariable(value = "id") Integer id, @RequestBody Turma turmaInfo) {
		Turma turma = repository.findById(id);

		if (turma == null) {
			return ResponseEntity.notFound().build();
		}

		if (!turmaInfo.getAno().isEmpty())
			turma.setAno(turmaInfo.getAno());
		if (turmaInfo.getComponenteCurricular() != null)
			turma.setComponenteCurricular(turmaInfo.getComponenteCurricular());
		if (turmaInfo.getId() != null)
			turma.setId(turmaInfo.getId());
		if (!turmaInfo.getNivel().isEmpty())
			turma.setNivel(turmaInfo.getNivel());
		if (!turmaInfo.getPeriodo().isEmpty())
			turma.setPeriodo(turmaInfo.getPeriodo());

		Turma updateTurma = repository.save(turma);
		return ResponseEntity.ok(updateTurma);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Turma> delete(@PathVariable(value = "id") Integer id) {
		Turma turma = repository.findById(id);
		if (turma == null)
			return ResponseEntity.notFound().build();

		repository.delete(turma);
		return ResponseEntity.ok().build();
	}

}
