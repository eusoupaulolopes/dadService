package com.ufrn.dad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Unidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_UNIDADE")
	@SequenceGenerator(name = "SEQ_UNIDADE", sequenceName = "id_seq_unidade", allocationSize = 1)
	@Column(name = "id_unidade")
	private Integer id;
	
	private String nome;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome_unidade(String nome) {
		this.nome = nome;
	}
	
	
}
