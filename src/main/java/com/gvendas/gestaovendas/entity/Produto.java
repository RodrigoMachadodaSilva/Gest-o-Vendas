package com.gvendas.gestaovendas.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo", length = 20)
	private Integer codigo;

	@NotBlank(message = "Descrição")
	@Length(min = 3, max = 100, message = "Descrição")
	@Column(name = "descricao")
	private String descricao;

	@NotNull(message = "Quantidade")
	@Column(name = "quantidade")
	private Integer quantidade;

	@NotNull(message = "Preço de Custo")
	@Column(name = "preco_custo")
	private BigDecimal preco_custo;

	@NotNull(message = "Preço de Venda")
	@Column(name = "preco_venda")
	private BigDecimal preco_venda;

	@Length(max = 500, message = "Observação")
	@Column(name = "observacao")
	private String observacao;

	@NotNull(message = "Código da Categoria")
	@ManyToOne
	@JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
	private Categoria categoria;

	public Produto(String descricao, Integer quantidade, BigDecimal preco_custo, BigDecimal preco_venda,
			String observacao, Categoria categoria) {
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco_custo = preco_custo;
		this.preco_venda = preco_venda;
		this.observacao = observacao;
		this.categoria = categoria;

	}

	public Produto(Integer codigo, String descricao, Integer quantidade, BigDecimal preco_custo, BigDecimal preco_venda,
			String observacao, Categoria categoria) {

		this.codigo = codigo;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco_custo = preco_custo;
		this.preco_venda = preco_venda;
		this.observacao = observacao;
		this.categoria = categoria;
	}

}
