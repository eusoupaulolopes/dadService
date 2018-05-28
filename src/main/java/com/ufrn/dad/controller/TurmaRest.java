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

import com.ufrn.dad.model.Turma;
import com.ufrn.dad.repository.TurmaRepository;

@RestController
@RequestMapping("turma")
public class TurmaRest {
	
	@Autowired
	public TurmaRepository repository;
	
	@GetMapping()
	public List<Turma> findAll() {
		return repository.findAll();
	}
	
	@PostMapping
	public Turma save(@Valid @RequestBody Turma turma) {
		return repository.save(turma);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Turma> update(@PathVariable(value = "id") Integer id,
			@RequestBody Turma turmaInfo) {
		Optional<Turma> turma = repository.findById(id);

		if (turma == null) {
			return ResponseEntity.notFound().build();
		}
		
		
		if (!turmaInfo.getAno().isEmpty())
			turma.get().setAno(turmaInfo.getAno());
		if (turmaInfo.getComponente_curricular() != null)
			turma.get().setComponente_curricular(turmaInfo.getComponente_curricular());
		if (turmaInfo.getId() != null)
			turma.get().setId(turmaInfo.getId());
		if (!turma.get().getNivel().isEmpty())
			turma.get().setNivel(turmaInfo.getNivel());
		if (!turma.get().getPeriodo().isEmpty())
			turma.get().setPeriodo(turmaInfo.getPeriodo());

		Turma updateTurma = repository.save(turma.get());
		return ResponseEntity.ok(updateTurma);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Turma> delete(@PathVariable(value = "id") Integer id) {
		Optional<Turma> turma = repository.findById(id);
		if (turma == null)
			return ResponseEntity.notFound().build();

		repository.delete(turma.get());
		return ResponseEntity.ok().build();
	}
	

}
