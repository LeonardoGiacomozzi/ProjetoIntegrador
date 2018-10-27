package edu.sit.view.menu;

import edu.sit.erro.leitura.EErroLeitura;

public class LeituraException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public LeituraException(EErroLeitura erroLerString) {
		super(erroLerString.getMensage());
	}

}
