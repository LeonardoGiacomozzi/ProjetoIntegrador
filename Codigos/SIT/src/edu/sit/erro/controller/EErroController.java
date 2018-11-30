package edu.sit.erro.controller;

public enum EErroController {
	
	ERRO_BUSCAR_CLIENTE_CPF("Erro ao buscar o cliente pelo cpf");
	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	private EErroController(String mensage) {
		this.mensage = mensage;
	}

}
