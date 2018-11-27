package edu.sit.erro.propriedades;



public class GravaArquivoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public GravaArquivoException(EErroPropriedade erro) {
		super(erro.getMensage());
	}

}
