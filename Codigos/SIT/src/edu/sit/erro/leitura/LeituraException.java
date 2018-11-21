package edu.sit.erro.leitura;

public class LeituraException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public LeituraException(EErroLeitura erro) {
		super(erro.getMensage());
	}

}
