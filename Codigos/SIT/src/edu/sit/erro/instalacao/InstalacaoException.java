package edu.sit.erro.instalacao;

public class InstalacaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public InstalacaoException(EErroInstalacao erro) {
		super(erro.getMensage());

	}



}
