package com.gvendas.gestaovendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.entity.Cliente;
import com.gvendas.gestaovendas.exception.RegraNegocioException;
import com.gvendas.gestaovendas.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> buscarPorCodigo(Integer codigo) {
		return clienteRepository.findById(codigo);

	}

	public Cliente salvar(Cliente cliente) {
		validarClienteDuplicado(cliente);
		return clienteRepository.save(cliente);
	}

	public Cliente atualizar(Integer codigo, Cliente cliente) {
		Cliente clienteAtualizar = ValidarClienteExiste(codigo);
		validarClienteDuplicado(cliente);
		BeanUtils.copyProperties(cliente, clienteAtualizar, "codigo");
		return clienteRepository.save(clienteAtualizar);

	}
	
	public void deletar(Integer codigo) {
		clienteRepository.deleteById(codigo);
	}

	public Cliente ValidarClienteExiste(Integer codigo) {
		Optional<Cliente> cliente = buscarPorCodigo(codigo);
		if (cliente.isEmpty()) {
			throw new EmptyResultDataAccessException(1);

		}
		return cliente.get();
	}

	public void validarClienteDuplicado(Cliente cliente) {
		Cliente clienteNome = clienteRepository.findByNome(cliente.getNome());
		if (clienteNome != null && clienteNome.getCodigo() != cliente.getCodigo()) {
			throw new RegraNegocioException(
					String.format("O cliente %s já está cadastrado", clienteNome).toUpperCase());
		}
		
	
		
		
	}

}