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
	public Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_docente", referencedColumnName = "id_docente")
	@JsonProperty("id_docente")
	public Docente docente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_turma", referencedColumnName = "id_turma")
	@JsonProperty("id_turma")
	public Turma turma;
	
	public Avaliacao(){
		super();
	}
	public Avaliacao(Integer id){
		super();
		this.id = id;
	}
	
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public Integer getQtdDiscentes() {
		return qtdDiscentes;
	}
	public void setQtdDiscentes(Integer qtdDiscentes) {
		this.qtdDiscentes = qtdDiscentes;
	}
	public Double getPosturaProfissional() {
		return posturaProfissional;
	}
	public void setPosturaProfissional(Double posturaProfissional) {
		this.posturaProfissional = posturaProfissional;
	}
	public Double getPosturaProfissionalDP() {
		return posturaProfissionalDP;
	}
	public void setPosturaProfissionalDP(Double posturaProfissionalDP) {
		this.posturaProfissionalDP = posturaProfissionalDP;
	}
	public Double getAtuacaoProfissional() {
		return atuacaoProfissional;
	}
	public void setAtuacaoProfissional(Double atuacaoProfissional) {
		this.atuacaoProfissional = atuacaoProfissional;
	}
	public Double getAtuacaoProfissionalDP() {
		return atuacaoProfissionalDP;
	}
	public void setAtuacaoProfissionalDP(Double atuacaoProfissionalDP) {
		this.atuacaoProfissionalDP = atuacaoProfissionalDP;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
