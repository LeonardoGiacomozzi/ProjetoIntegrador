package edu.sit.view.menu;

public class LeituraException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public LeituraException(EMensagensErroCad erro) {
		super(erro.getDescricao());
	}

}
