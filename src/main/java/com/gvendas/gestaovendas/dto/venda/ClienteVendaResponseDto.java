package com.gvendas.gestaovendas.dto.venda;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Vendas do Cliente Retorno Dto")
@Getter
@Setter
@AllArgsConstructor
public class ClienteVendaResponseDto {

	@ApiModelProperty(value = "Nome do Cliente")
	private String nome;

	@ApiModelProperty(value = "Vendas do Cliente")
	private List<VendaResponseDto> vendaResponseDto;

}
