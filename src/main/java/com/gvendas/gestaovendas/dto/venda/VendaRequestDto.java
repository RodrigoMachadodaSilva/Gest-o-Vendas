package com.gvendas.gestaovendas.dto.venda;

import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Venda requisição Dto")
public class VendaRequestDto {

	@ApiModelProperty(value = "Data")
	private LocalDate data;

	@ApiModelProperty(value = "Itens da Venda")
	private List<ItensVendaRequestDto> itensVendaRequestDto;

}
