package edu.sit.bancodedados.dao;

public enum EErrosDao {
	CRIAR_TABELA ("Erro ao criar a tabela especificada: "),
	EXCLUIR_TABELA ("Erro ao excluir a tabela especificada: "),
	CONSULTA_DADO ("Erro ao consultar dados na tabela especificada: "),
	INSERE_DADO ("Erro ao inserir dados na tabela especificada: "),
	ALTERA_DADO ("Erro ao alterar os dados da tabela especificada: "),
	EXCLUI_DADO ("Erro ao excluir os dados da tabela especificada: ");
	
	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	private EErrosDao(String mensagem) {
		this.mensagem = mensagem;
	}
}
