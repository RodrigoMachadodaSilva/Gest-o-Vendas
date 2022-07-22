package com.gvendas.gestaovendas.dto.venda;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel("Itens Venda Retorno Dto")
public class ItemVendaResponseDto {

	@ApiModelProperty(value = "Código")
	private Integer codigo;

	@ApiModelProperty(value = "Quantidadee")
	private Integer quantidade;

	@ApiModelProperty(value = "Preço Vendido")
	private BigDecimal precoVendido;

	@ApiModelProperty(value = "Código do Produto")
	private Integer codigoProduto;

	@ApiModelProperty(value = "Descrição do Produto")
	private String descricao;

}
