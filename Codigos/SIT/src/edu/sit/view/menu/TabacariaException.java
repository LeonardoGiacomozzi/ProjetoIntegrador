package edu.sit.view.menu;

public class TabacariaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public TabacariaException(EMensagensErroCad erro) {
		super(erro.getDescricao());
	}

}
