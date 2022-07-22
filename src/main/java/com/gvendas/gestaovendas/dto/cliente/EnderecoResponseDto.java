package com.gvendas.gestaovendas.dto.cliente;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.beans.factory.annotation.Autowired;

import com.gvendas.gestaovendas.entity.Endereco;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Endereço Response dto")
@Getter
@Setter
@AllArgsConstructor
public class EnderecoResponseDto {

	@ApiModelProperty(value = "Logradouro")
	private String logradouro;

	@ApiModelProperty(value = "Complemento")
	private String complemento;

	@ApiModelProperty(value = "Bairro")
	private String bairro;

	@ApiModelProperty(value = "Cep")
	private String cep;

	@ApiModelProperty(value = "Cidade")
	private String cidade;

	@ApiModelProperty(value = "Estado")
	private String estado;



}
