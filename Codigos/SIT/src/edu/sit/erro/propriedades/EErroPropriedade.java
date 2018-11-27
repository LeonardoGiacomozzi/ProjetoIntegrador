package edu.sit.erro.propriedades;

public enum EErroPropriedade {
	ERRO_Grava_Arquivo("Falha ao Gravar Arquivo"), 
	ERRO_ABRIR_ARQUIVO("Erro ao Abrir Arquivo"), 
	ERRO_CARREGAR_ARQUIVO("Erro ao Carregar Arquivo"), 
	ERRO_FECHAR_ARQUIVO("Erro ao Fechar Arquivo");
	
	

	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	private EErroPropriedade(String mensage) {
		this.mensage = mensage;
	}
	
}
