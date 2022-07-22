package com.gvendas.gestaovendas.dto.categoria;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.gvendas.gestaovendas.entity.Categoria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Categoria Requisição Dto")
public class CategoriaRequestDto {

	@ApiModelProperty(value = "Nome")
	@NotBlank(message = "Nome")
	@Length(min = 3, max = 50, message = "Nome")
	private String nome;

	public Categoria converterParaEntidade() {
		return new Categoria(nome);
	}

	public Categoria converterParaEntidade(Integer codigo) {
		return new Categoria(codigo, nome);
	}

}