package edu.sit.erro.controller;

public enum EErroController {
	
	ERRO_BUSCAR_CLIENTE_CPF("Erro ao buscar o cliente pelo Cpf"), 
	ERRO_BUSCAR_CNPJ_FORNECEDOR("Erro ao buscar o fornecedor pelo Cnpj"), 
	ERRO_BUSCAR_PRODUTO("Erro ao buscar o produto informado");
	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	private EErroController(String mensage) {
		this.mensage = mensage;
	}

}
