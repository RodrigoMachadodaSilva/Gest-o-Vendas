package com.gvendas.gestaovendas.dto.venda;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Itens da Venda Requisição Dto")
public class ItensVendaRequestDto {

	@ApiModelProperty(value = "Codigo do Produto")
	private Integer codigoProduto;

	@ApiModelProperty(value = "Quantidade")
	private Integer quantidade;

	@ApiModelProperty(value = "Preço do Produto")
	private BigDecimal preco_vendido;

}
