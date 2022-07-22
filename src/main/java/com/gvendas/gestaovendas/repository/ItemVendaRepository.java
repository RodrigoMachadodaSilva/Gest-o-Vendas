package com.gvendas.gestaovendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvendas.gestaovendas.entity.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Integer>{
	
	List<ItemVenda> findByVendaCodigo (Integer codigoVenda);

}
