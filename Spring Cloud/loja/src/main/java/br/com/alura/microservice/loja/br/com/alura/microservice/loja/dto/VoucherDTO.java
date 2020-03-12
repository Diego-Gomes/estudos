package br.com.alura.microservice.loja.br.com.alura.microservice.loja.dto;

import java.time.LocalDate;

public class VoucherDTO {

	private Long numero;
	
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public LocalDate getPrevisaoParaEntrega() {
		return previsaoParaEntrega;
	}

	public void setPrevisaoParaEntrega(LocalDate previsaoParaEntrega) {
		this.previsaoParaEntrega = previsaoParaEntrega;
	}

	private LocalDate previsaoParaEntrega;
}
