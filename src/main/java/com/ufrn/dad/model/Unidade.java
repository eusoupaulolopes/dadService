package com.ufrn.dad.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
public class Unidade {

	@Id
	@Column(name = "id_unidade")
	@JsonProperty("id_unidade")
	public Integer id;

	public String lotacao;

	// O mapeamento indica quem deve tomar conta do relacionamento, no caso é o
	// atributo 'unidade' da classe ComponenteCurricular
	// O modelo de cascata Persist diz o componente será afetado apenas nas
	// inserções e atualizações da unidade
	// JoinColumn indica qual o valor que referencia o relacionamento
	@OneToMany(mappedBy = "unidade", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JsonIgnore
	public List<ComponenteCurricular> componentes;

	@OneToMany(mappedBy = "unidade", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	public List<Docente> docentes;

	public Unidade() {
		super();
	}

	public Unidade(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLotacao() {
		return lotacao;
	}

	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}

	public List<ComponenteCurricular> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<ComponenteCurricular> componentes) {
		this.componentes = componentes;
	}

	public List<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

}
