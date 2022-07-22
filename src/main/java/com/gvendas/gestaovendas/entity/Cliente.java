package com.gvendas.gestaovendas.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo", length = 20)
	private Integer codigo;

	@Column(name = "nome", length = 50)
	private String nome;

	@Column(name = "telefone", length = 20)
	private String telefone;

	@Column(name = "ativo")
	private boolean ativo;

	@Embedded
	private Endereco endereco;

	public Cliente() {

	}

	public Cliente(String nome, String telefone, boolean ativo, Endereco endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.ativo = ativo;
		this.endereco = endereco;
	}

	public boolean getAtivo() {
		return this.ativo;
	}
}