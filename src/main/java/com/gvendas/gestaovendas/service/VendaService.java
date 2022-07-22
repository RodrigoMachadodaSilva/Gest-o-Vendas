package com.gvendas.gestaovendas.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDto;
import com.gvendas.gestaovendas.dto.venda.ItemVendaResponseDto;
import com.gvendas.gestaovendas.dto.venda.VendaResponseDto;
import com.gvendas.gestaovendas.entity.Cliente;
import com.gvendas.gestaovendas.entity.ItemVenda;
import com.gvendas.gestaovendas.entity.Venda;
import com.gvendas.gestaovendas.exception.RegraNegocioException;
import com.gvendas.gestaovendas.repository.ItemVendaRepository;
import com.gvendas.gestaovendas.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ItemVendaRepository itemVendaRepository;

	public ClienteVendaResponseDto listarVendaPorClienteRetornoDto(Integer codigoCliente) {
		Cliente cliente = validarVendaClienteExiste(codigoCliente);
		List<VendaResponseDto> vendaResponseDtoList = vendaRepository.findByClienteCodigo(codigoCliente)
				.stream().map(this::criandoVendaResponseDto).collect(Collectors.toList());
		return new ClienteVendaResponseDto(cliente.getNome(), vendaResponseDtoList);

	}
	
	public ClienteVendaResponseDto listarVendaPorCodigo( Integer codigoVenda) {
		Venda venda = validarVendaExiste(codigoVenda);
		return new ClienteVendaResponseDto(venda.getCliente().getNome(), Arrays.asList(criandoVendaResponseDto(venda)));
		
	}
	
	private Venda validarVendaExiste(Integer codigoVenda) {
		Optional<Venda> vendaExistente = vendaRepository.findById(codigoVenda);
		 if(vendaExistente.isEmpty()) {
			 throw new RegraNegocioException(String.format("A venda de código %s não está cadastrada.",codigoVenda ));
		 }
		 return vendaExistente.get();
	}

	private Cliente validarVendaClienteExiste(Integer codigoCliente) {
		Optional<Cliente> cliente = clienteService.buscarPorCodigo(codigoCliente);
		if (cliente.isEmpty()) {
			throw new RegraNegocioException(
					String.format("O Cliente de código %s informado não existe no cadastro", codigoCliente));
		}
		return cliente.get();
	}

	private VendaResponseDto criandoVendaResponseDto(Venda venda) {
		List<ItemVendaResponseDto> itensVendaResponseDto = itemVendaRepository.findByVendaCodigo(venda.getCodigo())
				.stream().map(this::criandoItensVendaResponseDto).collect(Collectors.toList());
		return new VendaResponseDto(venda.getCodigo(), venda.getData(), itensVendaResponseDto);

	}

	private ItemVendaResponseDto criandoItensVendaResponseDto(ItemVenda itemVenda) {
		return new ItemVendaResponseDto(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(),
				itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getDescricao());

	}

}
