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

import com.gvendas.gestaovendas.dto.produto.ProdutoRequestDto;
import com.gvendas.gestaovendas.dto.produto.ProdutoResponseDto;
import com.gvendas.gestaovendas.entity.Produto;
import com.gvendas.gestaovendas.service.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produto")
@RestController
@RequestMapping("categoria{codigoCategoria}/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@ApiOperation(value = "Salvar", nickname = "Salvar Produto")
	@PostMapping
	public ResponseEntity<ProdutoResponseDto> salvarProduto(@PathVariable Integer codigoCategoria,@Valid @RequestBody ProdutoRequestDto produtoRequestDto) {
		Produto produtoSalvo = produtoService.salvar(codigoCategoria,produtoRequestDto.converterProdutodtoParaEntidade(codigoCategoria));
		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDto.converterProdutoParaDto(produtoSalvo));
	}

	@ApiOperation(value = "Listar")
	@GetMapping
	public List<ProdutoResponseDto> listarTodas(@PathVariable Integer codigoCategoria) {
		return produtoService.listarTodos(codigoCategoria).stream()
				.map(produto -> ProdutoResponseDto.converterProdutoParaDto(produto)).collect(Collectors.toList());
	}

	@GetMapping("/{codigo}")
	@ApiOperation(value = "Listar por Código", nickname = "Buscar por Código ")
	public ResponseEntity<ProdutoResponseDto> buscarPorId(@PathVariable Integer codigoCategoria,
			@PathVariable Integer codigo) {
		Optional<Produto> produto = produtoService.buscarPorCodigo(codigo, codigoCategoria);
		return produto.isPresent() ? ResponseEntity.ok(ProdutoResponseDto.converterProdutoParaDto(produto.get())) : ResponseEntity.notFound().build();

	}

	@ApiOperation(value = "Atualizar", nickname = "Atualizar Produto")
	@PutMapping("{codigo}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer codigoCategoria, @PathVariable Integer codigo,
			@Valid @RequestBody Produto produto) {
		return ResponseEntity.ok(produtoService.atualizar(codigoCategoria, codigo, produto));
	}

	@ApiOperation(value = "Deletar", nickname = "Deletar Produto")
	@DeleteMapping("/codigo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarProduto(@PathVariable Integer codigoCategoria, @PathVariable Integer codigo) {
		produtoService.deletar(codigoCategoria, codigo);
	}

}
