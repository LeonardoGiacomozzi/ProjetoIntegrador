package edu.sit.erro.cadastro;

public enum EErroCadastro {
	
	ERRO_CADASTRO_CLIENTE("Erro ao cadastrar o cliente");
	
	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	private EErroCadastro(String mensage) {
		// TODO Auto-generated constructor stub
	}

}
