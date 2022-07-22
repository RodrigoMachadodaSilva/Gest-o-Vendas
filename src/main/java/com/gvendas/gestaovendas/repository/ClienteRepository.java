package com.gvendas.gestaovendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvendas.gestaovendas.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	Cliente findByNome( String nome);

}
