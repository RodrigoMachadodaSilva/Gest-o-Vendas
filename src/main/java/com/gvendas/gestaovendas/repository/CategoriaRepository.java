package com.gvendas.gestaovendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvendas.gestaovendas.entity.Categoria;

public interface CategoriaRepository  extends JpaRepository<Categoria, Integer>{
	
	Categoria findByNome(String nome);
}
