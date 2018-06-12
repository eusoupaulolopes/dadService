package com.ufrn.dad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
public class Turma {

	@Id
	@Column(name = "id_turma")
	@JsonProperty("id_turma")
	public Integer id;
	
	public String ano;
	
	public String nivel;
	
	public String periodo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_componente", referencedColumnName = "id_componente_curricular")
	@JsonProperty("id_componente")
	public ComponenteCurricular componenteCurricular;
	
	
	public Turma(){
		super();
	}
	
	public Turma(Integer id){
		super();
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public ComponenteCurricular getComponenteCurricular() {
		return componenteCurricular;
	}

	public void setComponenteCurricular(ComponenteCurricular componenteCurricular) {
		this.componenteCurricular = componenteCurricular;
	}
	

	
	
}
