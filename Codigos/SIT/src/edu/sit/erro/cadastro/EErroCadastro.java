package edu.sit.erro.cadastro;

public enum EErroCadastro {
	
	ERRO_CADASTRO_CLIENTE("Erro ao cadastrar o cliente"),
	ERRO_CADASTRO_CONTATO("Erro ao cadastrar o contato"),
	ERRO_CADASTRO_FUNCIONARIO("Erro ao cadastrar o funcionario"),
	ERRO_CADASTRO_CATEGORIA("Erro ao cadastrar a categoria"),
	ERRO_CADASTRO_FORNECEDOR("Erro ao cadastrar o fornecedor"),
	ERRO_CADASTRO_PRODUTO("Erro ao cadastrar o produto");
	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	private EErroCadastro(String mensage) {
		// TODO Auto-generated constructor stub
	}

}
