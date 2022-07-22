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
import com.gvendas.gestaovendas.entity.Categoria;
import com.gvendas.gestaovendas.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@ApiOperation(value = "Listar Categoria", nickname = "Listar Todas ")
	@GetMapping
	public List<CategoriaResponseDto> listarTodas() {
		return categoriaService.listarTodas()
				.stream().map(categoria -> CategoriaResponseDto.converterCategoriaParaDto( categoria))
				.collect(Collectors.toList());
		
	}
	
	@ApiOperation(value = "Listar Categoria por Código", nickname = "Buscar por Código")
	@GetMapping("/{codigo}")
	public ResponseEntity<CategoriaResponseDto> buscarPorId (@PathVariable Integer codigo) {
		Optional<Categoria> categoria = categoriaService.buscarPorCodigo(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(CategoriaResponseDto.converterCategoriaParaDto( categoria.get())): ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Salvar Categoria", nickname = "Salvar")
	@PostMapping
	public ResponseEntity<CategoriaResponseDto> salvar ( @Valid@RequestBody CategoriaRequestDto categoriaRequestDto) {
		Categoria categoriaSalva =  categoriaService.salvar(categoriaRequestDto.converterParaEntidade());
		return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDto.converterCategoriaParaDto(categoriaSalva));
	}
	
	@ApiOperation(value = "Atualizar Categoria por Código", nickname = "Atualizar")
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@Valid@PathVariable Integer codigo,@RequestBody CategoriaRequestDto categoriaRequestDto) {
	 return	ResponseEntity.ok(categoriaService.atualizar(codigo, categoriaRequestDto.converterParaEntidade(codigo)));
		
	}
	
	@ApiOperation(value = " Deletar Categoria por Código", nickname = "Deletar")
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer codigo) {
		categoriaService.deletarCategoria(codigo);
	
	}

}
