package edu.sit.erro.visualizacao;

public class VisualizacaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public VisualizacaoException(EErroVisualizacao erro) {
		super(erro.getMensagem());
	}
}
