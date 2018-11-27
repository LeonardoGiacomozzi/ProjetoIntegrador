package edu.sit.erro.propriedades;



public class PropriedadesException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public PropriedadesException(EErroPropriedade erro) {
		super(erro.getMensage());
	}

}
