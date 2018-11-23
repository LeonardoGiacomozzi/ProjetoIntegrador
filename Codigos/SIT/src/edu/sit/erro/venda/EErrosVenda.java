package edu.sit.erro.venda;

public enum EErrosVenda {

	BUSCA_LISTA_PRODUTO("Erro ao buscar os produtos "),
	BUSCA_LISTA_CLIENTE("Erro ao buscar os clientes");
	
	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	private EErrosVenda(String mensagem) {
		this.mensagem = mensagem;
	}
}
