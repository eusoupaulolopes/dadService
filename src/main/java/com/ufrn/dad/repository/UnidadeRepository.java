/**
 * 
 */
package com.ufrn.dad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufrn.dad.model.Unidade;

/**
 * @author Paulo
 *
 */
public interface UnidadeRepository  extends JpaRepository<Unidade, Integer> {

	/**
	 * Busca de unidades pelo nome
	 * @param nome
	 * @return
	 */
	Unidade findByNome(String nome);
	
	
	
	
	
}
