package edu.sit.erro.instalacao;

public enum EErroInstalacao {
	ERRO_POPULAR_CLIENTES("Erro ao tentar popular a tabela de cliente no banco"), 
	ERRO_POPULAR_FUNCIONARIO("Erro ao tentar popular a tabela de funcionario no banco"), 
	ERRO_POPULAR_FORNECEDOR("Erro ao tentar popular a tabela de fornecedore no banco"), 
	ERRO_POPULAR_CATEGORIA("Erro ao tentar popular a tabela de categoria no banco"),
	ERRO_POPULAR_VENDA("Erro ao tentar popular a tabela de categoria no banco"),
	ERRO_POPULAR_USUARIO("Erro ao tentar popularr a tabela de usuarios");

	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	
	private EErroInstalacao(String mensagem) {
		
	}
}
