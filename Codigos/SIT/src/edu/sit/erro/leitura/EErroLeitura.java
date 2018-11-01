package edu.sit.erro.leitura;

public enum EErroLeitura {
	
	ERRO_LER_STRING("Informe um valor valido"),
	ERRO_LER_INTEGER("Informe um valor Interio"),
	ERRO_LER_DOUBLE("Informe um valor do tipo Double"), 
	ERRO_LER_DATA ("Informe uma data no formato dia/mês/ano (dd/mm/yyyy)");
	
	

	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	private EErroLeitura(String mensage) {
		
	}
	
}
