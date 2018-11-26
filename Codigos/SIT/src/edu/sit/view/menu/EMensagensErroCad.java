package edu.sit.view.menu;

public enum EMensagensErroCad {
	
	ERRO_CLIENTE("ALGO ESTA ERRADO"),
	ERRO_FUNCIONARIO("N�O FOI"),
	ERRO_PRODUTO("PRODUTO COM ERRO"),
	ERRO_FORNECEDOR("ESTA ERRADO"),
	ERRO_VENDAS("VENDA ERRADA"),
	ERRO_CONTROLE_VENDA("ERRADO"),
	ERRO_ESTOQUE("TO ERRADO"),
	ERRO_ESCRITA("Op��o Inv�lida! Escolha apenas N�meros que cont�m nas op��es acima.");
	
	
	private final String descricao;

	public String getDescricao() {
		return descricao;
	}
	
	private EMensagensErroCad(String descricao) {
		this.descricao = descricao;
	}
}
