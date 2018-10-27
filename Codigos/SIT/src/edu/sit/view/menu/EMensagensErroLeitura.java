package edu.sit.view.menu;

public enum EMensagensErroLeitura {
	ERRO_CARACTER("Carecter invalido");
	private final String descricao;

	public String getDescricao() {
		return descricao;
	}
	
	private EMensagensErroLeitura(String descricao) {
		this.descricao = descricao;
		
	}
}


