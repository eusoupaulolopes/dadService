package com.ufrn.dad.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.engine.jdbc.SerializableBlobProxy;

@Entity
@Table
public class Unidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_UNIDADE")
	@SequenceGenerator(name = "SEQ_UNIDADE", sequenceName = "id_seq_unidade", allocationSize = 1)
	@Column(name = "id_unidade")
	private Integer id;
	
	private String nome;
	
	// O mapeamento indica quem deve tomar conta do relacionamento, no caso é o atributo 'unidade' da classe ComponenteCurricular
	// O modelo de cascata Persist diz o componente será afetado apenas nas inserções e atualizações da unidade
	// JoinColumn indica qual o valor que referencia o relacionamento
	@OneToMany(mappedBy="unidade", fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	private List<ComponenteCurricular> componentes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ComponenteCurricular> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<ComponenteCurricular> componentes) {
		this.componentes = componentes;
	}
	
	
	
}
