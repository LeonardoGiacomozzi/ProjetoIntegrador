package edu.sit.bancodedados.dao;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DaoException(EErrosDao erro, String mensagem, Class<?> classe) {
		super(erro.getMensagem() + classe.getName() + "#" + mensagem);
	}
}
