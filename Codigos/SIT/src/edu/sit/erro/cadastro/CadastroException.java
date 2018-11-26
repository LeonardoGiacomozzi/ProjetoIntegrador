package edu.sit.erro.cadastro;


public class CadastroException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CadastroException(EErroCadastro erro) {
		super(erro.getMensage());
	}

}
