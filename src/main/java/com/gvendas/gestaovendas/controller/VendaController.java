package com.gvendas.gestaovendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDto;
import com.gvendas.gestaovendas.service.VendaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/venda")
@Api(tags = "Venda")
public class VendaController {

	@Autowired
	private VendaService vendaService;

	@ApiOperation(value = "Listar Vendas por Cliente", nickname = "listarVendaPorCliente")
	@GetMapping("cliente/{codigoCliente}")
	public ResponseEntity<ClienteVendaResponseDto> listarVendaporCliente(@PathVariable Integer codigoCliente ) {
		return ResponseEntity.ok(vendaService.listarVendaPorClienteRetornoDto(codigoCliente));
	
	}

	@ApiOperation(value = "Listar Vendas por Còdigo", nickname = "listarVendaPorCodigo")
	@GetMapping("/{codigoVenda}")
	public ResponseEntity<ClienteVendaResponseDto> listarVendaPorCodigo(@PathVariable Integer codigoCliente) {
		return ResponseEntity.ok(vendaService.listarVendaPorCodigo(codigoCliente));

	}

}
