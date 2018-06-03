package com.ufrn.dad.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
public class Avaliacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_avaliacao")
	@JsonIgnore
	public Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_docente", referencedColumnName = "id_docente")
	@JsonProperty("id_docente")
	public Docente docente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_turma", referencedColumnName = "id_turma")
	@JsonProperty("id_turma")
	public Turma turma;
	
	@JsonProperty("qtd_discentes")
	public Integer qtdDiscentes;
	
	@JsonProperty("postura_profissional")
	public Double posturaProfissional;
	
	@JsonProperty("postura_profissional_DP")
	public Double posturaProfissionalDP;
	
	@JsonProperty("atuacao_profissional")
	public Double atuacaoProfissional;
	
	@JsonProperty("atuacao_profissional_DP")
	public Double atuacaoProfissionalDP;
	
	

}
