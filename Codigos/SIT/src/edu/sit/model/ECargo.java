package edu.sit.model;

public enum ECargo {
	VENDEDOR("Vendedor"),
	GERENTE("Gerente");
	
	private String descricao;

	public String getDescricao() {
		return this.descricao;
	}

	private ECargo(String descricao) {
		this.descricao = descricao;
	}

	
	
}
