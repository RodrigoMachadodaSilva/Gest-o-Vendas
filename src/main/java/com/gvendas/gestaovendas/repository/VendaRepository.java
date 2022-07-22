package com.gvendas.gestaovendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvendas.gestaovendas.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
	
	List<Venda> findByClienteCodigo( Integer codigoCliente);

}
