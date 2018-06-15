package com.ufrn.dad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.dad.model.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {

	List<Docente> findAllByNome(String nome);

}
