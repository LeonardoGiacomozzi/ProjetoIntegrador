package edu.sit.erro.propriedades;

public enum EErroPropriedade {
	ERRO_SALVAR_PROPRIEDADES("Erro ao salvar o arquivo de propriedades do sistema");
	

	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	private EErroPropriedade(String mensage) {
		this.mensage = mensage;
	}
	
}
