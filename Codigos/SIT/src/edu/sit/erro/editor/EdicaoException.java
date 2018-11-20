package edu.sit.erro.editor;


public class EdicaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EdicaoException (EErroEdicao erro) {
		super(erro.getMensage());
	}

}
