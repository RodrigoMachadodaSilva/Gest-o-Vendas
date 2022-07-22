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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item_venda")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class ItemVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "preco_vendido")
	private BigDecimal precoVendido;
	
	@Column(name = "preco_custo")
	private BigDecimal precoCusto;

	@ManyToOne
	@JoinColumn(name = "codigo_produto", referencedColumnName = "codigo")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "codigo_venda", referencedColumnName = "codigo")
	private Venda venda;

}
