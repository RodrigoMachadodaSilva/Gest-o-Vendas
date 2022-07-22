package com.gvendas.gestaovendas.dto.cliente;

import com.gvendas.gestaovendas.entity.Cliente;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Cliente Retorno Dto")
@Getter
@Setter
@AllArgsConstructor
public class ClienteResponseDto {

	@ApiModelProperty(value = "Codigo")
	private Integer codigo;

	@ApiModelProperty(value = "Nome")
	private String nome;

	@ApiModelProperty(value = "Telefone")
	private String telefone;

	@ApiModelProperty(value = "Ativo")
	private boolean ativo;

	private EnderecoResponseDto enderecoDto;

	public static ClienteResponseDto converterParaClienteDto(Cliente cliente) {
		EnderecoResponseDto endereco = new EnderecoResponseDto(cliente.getEndereco().getLogradouro(), cliente.getEndereco().getComplemento(), cliente.getEndereco().getBairro(),
				cliente.getEndereco().getCep(), cliente.getEndereco().getCidade(), cliente.getEndereco().getEstado());
		return new ClienteResponseDto(cliente.getCodigo(), cliente.getNome(), cliente.getTelefone(),
				cliente.getAtivo(), endereco);

	}

}
