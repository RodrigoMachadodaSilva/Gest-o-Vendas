package com.gvendas.gestaovendas.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Api()
@Entity
@Table(name = "venda")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "data")
	private LocalDate data;

	@ManyToOne
	@JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo")
	private Cliente cliente;

}
