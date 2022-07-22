package com.gvendas.gestaovendas.dto.cliente;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.gvendas.gestaovendas.entity.Cliente;
import com.gvendas.gestaovendas.entity.Endereco;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Cliente Resposta Dto")
@Getter
@Setter
@AllArgsConstructor
public class ClienteRequestDto {
	

	@ApiModelProperty(value = "Nome")
	@NotBlank(message = "Nome")
	@Length(min = 3, max = 50, message = "Nome" )
	private String nome;

	@ApiModelProperty(value = "Telefone")
	@NotBlank( message = "Telefone")
	//@Pattern(regexp = "\\([\\d]{2}\\)[\\d]{5}[- .][\\d]{4}")
	private String telefone;
	
	@NotNull(message = "Ativo")
	private boolean ativo;
	
	@ApiModelProperty(value = "Endereço")
	@NotNull(message = "Endereço")
	@Valid
	private EnderecoRequestDto enderecoDto;
	
	public Cliente converterParaEntidade() {
		 Endereco endereco = new Endereco(enderecoDto.getBairro(),enderecoDto.getCep(), enderecoDto.getCidade(),
				enderecoDto.getComplemento(),enderecoDto.getEstado(), enderecoDto.getLogradouro());
		return new Cliente(nome, telefone, ativo, endereco);
		
	}
	
	public Cliente converterParaEntidade(Integer codigo) {
		 Endereco endereco = new Endereco(enderecoDto.getBairro(),enderecoDto.getCep(), enderecoDto.getCidade(),
				enderecoDto.getComplemento(),enderecoDto.getEstado(), enderecoDto.getLogradouro());
		return new Cliente(codigo ,nome, telefone, ativo, endereco);
		
	}

}
