package edu.sit.bancodedados.conexao;

public enum EErrosConexao {
	ABRE_CONEXAO ("Erro de Conex�o: N�o foi poss�vel estabelecer conex�o com o Banco de Dados."),
	FECHA_CONEXAO ("Erro de Conex�o: N�o foi poss�vel fechar a conex�o com o Banco de Dados.");
	
	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}
	
	private EErrosConexao(String mensagem) {
		this.mensagem = mensagem;
	}
}
