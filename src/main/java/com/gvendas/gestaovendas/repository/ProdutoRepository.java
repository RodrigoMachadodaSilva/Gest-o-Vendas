package com.gvendas.gestaovendas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gvendas.gestaovendas.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	//Busca todos produtos pelo codigo da categoria
	List<Produto> findByCategoriaCodigo (Integer codigoCategoria);
	
	
	//Busca o produto pelo codigo da categoria e pelo codigo do produto
	@Query("Select prod" 
	        + " from Produto prod"  
			+ " where prod.codigo = :codigo"
			+ "  and prod.categoria.codigo = :codigoCategoria")
	Optional<Produto> buscarPorCodigo(Integer codigo, Integer codigoCategoria);
	//findByCodigoAndCategoriaCodigo
	
	
	Optional<Produto> findByCategoriaCodigoAndDescricao (Integer codigoCategoria, String Descricao);
	

}
