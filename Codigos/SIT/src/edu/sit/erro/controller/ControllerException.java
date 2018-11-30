package edu.sit.erro.controller;


public class ControllerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ControllerException(EErroController erro) {
		super(erro.getMensage());
	}

}
