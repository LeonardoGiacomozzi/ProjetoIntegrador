package edu.sit.erro.instalacao;

public enum EErroInstalacao {
	ERRO_POPULAR_CLIENTES("Erro ao tentar popular a tabela de clientes no banco"), 
	ERRO_POPULAR_FUNCIONARIO("Erro ao tentar popular a tabela de clientes no banco");

	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	
	private EErroInstalacao(String mensagem) {
		
	}
}
