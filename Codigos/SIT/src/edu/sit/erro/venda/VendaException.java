package edu.sit.erro.venda;

public class VendaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public VendaException(EErrosVenda erro) {
		super(erro.getMensagem());
	}
}
