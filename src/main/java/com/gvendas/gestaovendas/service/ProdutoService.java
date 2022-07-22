package com.gvendas.gestaovendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.entity.Produto;
import com.gvendas.gestaovendas.exception.RegraNegocioException;
import com.gvendas.gestaovendas.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaService categoriaService;

	public List<Produto> listarTodos(Integer codigoCategoria) {
		return produtoRepository.findByCategoriaCodigo(codigoCategoria);

	}

	public Optional<Produto> buscarPorCodigo(Integer codigo, Integer codigoCategoria) {
		return produtoRepository.buscarPorCodigo(codigo, codigoCategoria);
	}

	public Produto salvar(Integer codigoCategoria, Produto produto) {
		validarCategoriaProduto(produto.getCategoria().getCodigo());
		validarProdutoDuplicado(produto);
		return produtoRepository.save(produto);

	}
	
	//Atualiza o produto verificando se ele existe no banco de dados
	public Produto atualizar (Integer codigoCategoria, Integer codigo, Produto produto) {
		Produto produtoSalvar = validarProdutoExistente(codigo, codigoCategoria);
		validarCategoriaProduto(codigoCategoria);
		validarProdutoDuplicado(produto);
		BeanUtils.copyProperties(produto, produtoSalvar, "codigo");
		return produtoRepository.save(produtoSalvar);
		
		
	}
	
	public void deletar (Integer codigoCategoria, Integer codigo) {
		Produto produto = validarProdutoExistente( codigoCategoria, codigo);
		produtoRepository.delete(produto);
		
	}
	
    //Método que valida se o produto já está salvo nno banco de dados
	private Produto validarProdutoExistente(Integer codigo, Integer codigoCategoria) {
		Optional<Produto> produto = buscarPorCodigo(codigo, codigoCategoria);
		if(produto.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return produto.get();
		
		
	}

	// Método para validar se a categoria do produto, já foi cadastrada.
	private void validarCategoriaProduto(Integer codigoCategoria) {
		if (codigoCategoria == null) {
			throw new RegraNegocioException("A categoria não pode ser nula");

		}
		if (categoriaService.buscarPorCodigo(codigoCategoria).isEmpty()) {
			throw new RegraNegocioException(
					String.format("A categoria de códio %s informada não existe no cadastro", codigoCategoria));
		}

	}

	@SuppressWarnings("unused")
	private void validarProdutoDuplicado(Produto produto) {
		if (produtoRepository
				.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao())
				.isPresent()) {
			throw new RegraNegocioException(String.format("O pruduto %s já está cadastrado", produto.getDescricao()));
		}

	}

}
