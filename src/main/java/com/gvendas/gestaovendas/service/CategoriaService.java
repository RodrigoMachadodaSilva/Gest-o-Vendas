package com.gvendas.gestaovendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.entity.Categoria;
import com.gvendas.gestaovendas.exception.RegraNegocioException;
import com.gvendas.gestaovendas.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> listarTodas() {
		return categoriaRepository.findAll();
	}

	public Optional<Categoria> buscarPorCodigo(Integer codigo) {
		return categoriaRepository.findById(codigo);

	}

	public Categoria salvar(Categoria categoria) {
		validarCategoriaDuplicada(categoria);
		return categoriaRepository.save(categoria);

	}

	public void deletarCategoria(Integer codigo) {
		categoriaRepository.deleteById(codigo);
	}

	public Categoria atualizar(Integer codigo, Categoria categoria) {
		Categoria categoriaSalva = validarCategoriaExistente(codigo);
		validarCategoriaDuplicada(categoria);
		// Substitui a categoria salva no banco, pelo objeto enviado na requisição.
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
		return categoriaRepository.save(categoriaSalva);

	}

	/* Método que valida a categoria salva no banco analizando o id */
	private Categoria validarCategoriaExistente(Integer codigo) {
		Optional<Categoria> categoria = buscarPorCodigo(codigo);
		if (categoria.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoria.get();

	}

	/*
	 * Método que verifica se a categoria está cadastrada no banco, permitindo que
	 * ela seja salva ou alterada, somente se ela não existir no banco e o identificador
	 *  for o mesmo, permitindo fazer a operação caso o identificador seja diferente,
	 *  caso por exemplo, desejarmos corrigir um cadastro errado.
	 */
	private void validarCategoriaDuplicada(Categoria categoria) {
		Categoria categoriaExistente = categoriaRepository.findByNome(categoria.getNome());
		if (categoriaExistente != null && categoriaExistente.getCodigo() != categoria.getCodigo()) {
			throw new RegraNegocioException(
					String.format("A categoria %s já está cadastrada", categoria.getNome().toUpperCase()));
		}

	}

}