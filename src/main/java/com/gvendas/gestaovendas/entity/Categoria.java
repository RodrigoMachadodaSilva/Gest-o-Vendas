package com.gvendas.gestaovendas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo", length = 20)
	private Integer codigo;

	@Column(name = "nome", length = 50)
	private String nome;

	// @OneToMany
	// private List<Produto> produtos;

	public Categoria() {

	}

	public Categoria(String nome) {
		this.nome = nome;
	}
	

	public Categoria(Integer codigo) {
		this.codigo = codigo;
	}

	public Categoria(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

}
