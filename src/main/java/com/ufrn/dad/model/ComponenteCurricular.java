package com.ufrn.dad.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
public class ComponenteCurricular implements Serializable{
	
	/**
	 * 
	 */
	public static final long serialVersionUID = 3766220200551972311L;

	@Id
//	@SequenceGenerator(name = "SEQ_COMPONENTE_CURRICULAR", sequenceName = "id_seq_componente_curricular", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPONENTE_CURRICULAR")
	@Column(name = "id_componente_curricular")
	@JsonProperty("id_componente")
	public Integer id;
	
	public String codigo;
	
	@Column(name="nome_componente_curricular")
	@JsonProperty("nome")
	public String nomeComponenteCurricular;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_unidade", referencedColumnName = "id_unidade")
	@JsonProperty("id_unidade")
	public Unidade unidade;
	
	@OneToMany(mappedBy = "componenteCurricular", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JsonIgnore
	public List<Turma> turmas;
	
	public ComponenteCurricular(){
		super();
	}
	
	public ComponenteCurricular(Integer id) {
		this.id = id;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getNomeComponenteCurricular() {
		return nomeComponenteCurricular;
	}


	public void setNomeComponenteCurricular(String nomeComponenteCurricular) {
		this.nomeComponenteCurricular = nomeComponenteCurricular;
	}


	public Unidade getUnidade() {
		return unidade;
	}


	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}


	public List<Turma> getTurmas() {
		return turmas;
	}


	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	
	
}
