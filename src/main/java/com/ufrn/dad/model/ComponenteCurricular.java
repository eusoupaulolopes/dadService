package com.ufrn.dad.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class ComponenteCurricular {
	
	@Id
	@SequenceGenerator(name = "SEQ_COMPONENTE_CURRICULAR", sequenceName = "id_seq_componente_curricular", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPONENTE_CURRICULAR")
	@Column(name = "id_componente_curricular")
	private Integer id;
	
	private String codigo;
	
	@Column(name="nome_componente_curricular")
	private String nomeComponenteCurricular;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;


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


	
	

}
