package com.gvendas.gestaovendas.dto.produto;

import java.math.BigDecimal;

import com.gvendas.gestaovendas.dto.categoria.CategoriaResponseDto;
import com.gvendas.gestaovendas.entity.Produto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Produto Resposta Dto")
@AllArgsConstructor
public class ProdutoResponseDto {

	@ApiModelProperty(value = "Código")
	private Integer codigo;

	@ApiModelProperty(value = "Descrição")
	private String descricao;

	@ApiModelProperty(value = "Quantidade")
	private Integer quantidade;

	@ApiModelProperty(value = "Preço de Custo")
	private BigDecimal preco_custo;

	@ApiModelProperty(value = "Preço de Venda")
	private BigDecimal preco_venda;

	@ApiModelProperty(value = "Observação")
	private String observacao;

	@ApiModelProperty(value = "Categoria")
	private CategoriaResponseDto categoria;

	public static ProdutoResponseDto converterProdutoParaDto(Produto produto) {
		return new ProdutoResponseDto(produto.getCodigo(), produto.getDescricao(), produto.getQuantidade(),
				produto.getPreco_custo(), produto.getPreco_venda(), produto.getObservacao(),
				CategoriaResponseDto.converterCategoriaParaDto(produto.getCategoria()));
	}

}
