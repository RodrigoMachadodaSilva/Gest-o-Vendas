package com.gvendas.gestaovendas.dto.categoria;

import com.gvendas.gestaovendas.entity.Categoria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Categoria Dto")
@AllArgsConstructor
public class CategoriaResponseDto {

	@ApiModelProperty(value = "Código")
	private Integer codigo;

	@ApiModelProperty(name = "Nome")
	private String nome;

	public static CategoriaResponseDto converterCategoriaParaDto(Categoria categoria) {
		return new CategoriaResponseDto(categoria.getCodigo(), categoria.getNome());

	}

}
