package com.gvendas.gestaovendas.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class Endereco {



	@Column(name = "logradouro", length = 30)
	private String logradouro;

	@Column(name = "complemento", length = 30)
	private String complemento;

	@Column(name = "bairro", length = 30)
	private String bairro;

	@Column(name = "cep", length = 30)
	private String cep;

	@Column(name = "cidade", length = 30)
	private String cidade;

	@Column(name = "estado", length = 30)
	private String estado;
	
	public Endereco() {
	
	}

	public Endereco(String logradouro, String complemento, String bairro, String cep, String cidade, String estado) {
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
	}
	


}
