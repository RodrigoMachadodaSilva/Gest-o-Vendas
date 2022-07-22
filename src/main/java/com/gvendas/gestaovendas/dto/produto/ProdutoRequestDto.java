package com.gvendas.gestaovendas.dto.produto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.gvendas.gestaovendas.entity.Categoria;
import com.gvendas.gestaovendas.entity.Produto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Produto requisição Dto")
@Getter
@Setter
public class ProdutoRequestDto {

	@NotBlank(message = "Descrição")
	@Length(min = 3, max = 100, message = "Descrição")
	@ApiModelProperty(value = "Descrição")
	private String descricao;

	@ApiModelProperty(value = "Quantidade")
	@NotNull(message = "Quantidade")
	private Integer quantidade;

	@ApiModelProperty(value = "Preço de Custo")
	@NotNull(message = "Preço de Custo")
	private BigDecimal preco_custo;

	@ApiModelProperty(value = "Preço de Venda")
	@NotNull(message = "Preço de Venda")
	private BigDecimal preco_venda;

	@ApiModelProperty(value = "Observação")
	@Length(max = 500, message = "Observação")
	private String observacao;

	public Produto converterProdutodtoParaEntidade(Integer codigoCategoria) {
		return new Produto(descricao, quantidade, preco_custo, preco_venda, observacao, new Categoria(codigoCategoria));

	}
	
	//public Produto converterProdutodtoParaEntidade(Integer codigoCategoria, Integer codigoProduto) {
		//return new Produto(codigoProduto, quantidade, preco_custo, preco_venda, observacao, new Categoria(codigoCategoria));

	//}

}
