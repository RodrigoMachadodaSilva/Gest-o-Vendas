package com.gvendas.gestaovendas.dto.venda;

import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Venda Retorno Dto")
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class VendaResponseDto {

	@ApiModelProperty(value = "Item da venda")
	private Integer codigo;

	@ApiModelProperty(value = "Data da venda")
	private LocalDate data;

	@ApiModelProperty("Itens da Venda")
	private List<ItemVendaResponseDto> itemVendaResponseDto;

}
