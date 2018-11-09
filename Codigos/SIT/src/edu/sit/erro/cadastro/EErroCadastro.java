package edu.sit.erro.cadastro;

public enum EErroCadastro {
	
	ERRO_CADASTRO_CLIENTE("Erro ao cadastrar o cliente"),
	ERRO_CADASTRO_CONTATO("Erro ao cadastrar o contato"),
	ERRO_CADASTRO_FUNCIONARIO("Erro ao cadastrar o funcionario");
	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	private EErroCadastro(String mensage) {
		// TODO Auto-generated constructor stub
	}

}
