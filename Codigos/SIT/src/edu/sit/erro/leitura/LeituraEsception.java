package edu.sit.erro.leitura;

public class LeituraEsception extends Exception{

	private static final long serialVersionUID = 1L;
	
	public LeituraEsception(EErroLeitura erro) {
		super(erro.getMensage());
	}

}
