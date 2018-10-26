package edu.sit.erro.notaFiscal;

public class NotaFiscalException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NotaFiscalException(EErroNotaFiscal erro) {
		super(erro.getMensage());
	}
}
