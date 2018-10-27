package edu.sit.bancodedados.conexao;

public class ConexaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConexaoException(EErrosConexao erro, String mensagem) {
		super(erro.getMensagem() + "#" + mensagem);
	}
}
