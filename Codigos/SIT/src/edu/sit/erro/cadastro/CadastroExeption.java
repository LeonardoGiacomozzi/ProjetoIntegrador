package edu.sit.erro.cadastro;


public class CadastroExeption extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CadastroExeption(EErroCadastro erro) {
		super(erro.getMensage());
	}

}
