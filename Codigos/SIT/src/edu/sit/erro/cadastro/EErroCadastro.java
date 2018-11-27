package edu.sit.erro.cadastro;

public enum EErroCadastro {
	
	ERRO_CADASTRO_CLIENTE("Erro ao cadastrar o cliente"),
	ERRO_CADASTRO_CONTATO("Erro ao cadastrar o contato"),
	ERRO_CADASTRO_FUNCIONARIO("Erro ao cadastrar o funcionário"),
	ERRO_CADASTRO_CATEGORIA("Erro ao cadastrar a categoria"),
	ERRO_CADASTRO_FORNECEDOR("Erro ao cadastrar o fornecedor"),
	ERRO_CADASTRO_PRODUTO("Erro ao cadastrar o produto"), 
	ERRO_CADASTRO_USUARIO("Erro ao cadastrar o Usuario");
	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	private EErroCadastro(String mensage) {
		this.mensage = mensage;
	}

}
