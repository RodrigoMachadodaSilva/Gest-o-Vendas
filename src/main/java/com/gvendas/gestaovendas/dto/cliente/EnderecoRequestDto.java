package com.gvendas.gestaovendas.dto.cliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@ApiModel("Endereço Requisição dto")
@Getter
@Setter
@AllArgsConstructor
public class EnderecoRequestDto {
	
	@NotBlank(message = "Logradouro")
	@Length(min = 3, max = 30, message = "Logradouro" )
	@ApiModelProperty(value = "Logradouro")
	private String logradouro;
	
	
	@Length( max = 50, message = "Complemento" )
	@ApiModelProperty(value = "Complemento")
	private String complemento;
	
	@NotBlank(message = "Bairro")
	@Length(min = 3, max = 30, message = "Bairro" )
	@ApiModelProperty(value = "Bairro")
	private String bairro;
	
	@NotBlank( message = "Cep")
	//@Pattern(regexp = "[\\d]{5}-[\\d]{3}")
	@ApiModelProperty(value = "Cep")
	private String cep;
	
	@NotBlank(message = "Cidade")
	@Length(min = 3, max = 30, message = "Cidade" )
	@ApiModelProperty(value = "Cidade")
	private String cidade;
	
	@NotBlank(message = "Estado")
	@Length( max = 30, message = "Estado" )
	@ApiModelProperty(value = "Estado")
	private String estado;

}
