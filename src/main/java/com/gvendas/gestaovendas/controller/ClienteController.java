package com.gvendas.gestaovendas.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gvendas.gestaovendas.dto.categoria.CategoriaRequestDto;
import com.gvendas.gestaovendas.dto.categoria.CategoriaResponseDto;
import com.gvendas.gestaovendas.dto.cliente.ClienteRequestDto;
import com.gvendas.gestaovendas.dto.cliente.ClienteResponseDto;
import com.gvendas.gestaovendas.entity.Categoria;
import com.gvendas.gestaovendas.entity.Cliente;
import com.gvendas.gestaovendas.service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cliente")
@Api(tags = "Cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@ApiOperation(value = "Listar Cliente", nickname = "Listar todos Clientes ")
	@GetMapping
	public List<ClienteResponseDto> listarTodos() {
		return clienteService.listarTodos().stream().map(cliente -> ClienteResponseDto.converterParaClienteDto(cliente))
				.collect(Collectors.toList());

	}

	@ApiOperation(value = "Listar Cliente por Código", nickname = "Buscar por Cliente por Id")
	@GetMapping("/{codigo}")
	public ResponseEntity<ClienteResponseDto> buscarPorId(@PathVariable Integer codigo) {
		Optional<Cliente> cliente = clienteService.buscarPorCodigo(codigo);
		return cliente.isPresent() ? ResponseEntity.ok(ClienteResponseDto.converterParaClienteDto(cliente.get())) : ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Salvar Cliente", nickname = "Salvar Cliente")
	@PostMapping
	public ResponseEntity<ClienteResponseDto> salvar ( @Valid @RequestBody ClienteRequestDto clienteRequestDto) {
		Cliente clienteSalvo =  clienteService.salvar(clienteRequestDto.converterParaEntidade());
		return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDto.converterParaClienteDto(clienteSalvo));
	}
	
	@ApiOperation(value = "Atualizar Cliente", nickname = "Atualizar Cliente")
	@PutMapping("/{codigo}")
	public ResponseEntity<ClienteResponseDto> atualizar(@PathVariable Integer codigo,@Valid @RequestBody ClienteRequestDto clienteRequestDto) {
		 Cliente clienteAtualizado = clienteService.atualizar(codigo, clienteRequestDto.converterParaEntidade(codigo));
		 return ResponseEntity.ok(ClienteResponseDto.converterParaClienteDto(clienteAtualizado));
	}
	
	@ApiOperation(value = "Deletar Cliente", nickname = "Deletar Cliente")
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente (@PathVariable Integer  codigo) {
		clienteService.deletar(codigo);
		
	}

}
