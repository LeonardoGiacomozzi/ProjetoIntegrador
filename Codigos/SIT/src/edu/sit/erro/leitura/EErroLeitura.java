package edu.sit.erro.leitura;

public enum EErroLeitura {
	
	ERRO_LER_STRING("Informe um valor válido..."),
	ERRO_LER_INTEGER("Informe um valor inteiro..."),
	ERRO_LER_DOUBLE("Informe um valor do tipo double (Ex: 10.5)..."), 
	ERRO_LER_DATA ("Informe uma data no formato dia/mês/ano (dd/mm/yyyy)..."),
	ERRO_LER_CPF ("Informe um CPF válido XXX.XXX.XXX-XX ..."),
	ERRO_LER_CNPJ("Informe um CNPJ válido XX.XXX.XXX/XXX-XX ..."),
	ERRO_LER_FONE("Informe um Telefone válido (XX)XXXX-XXXX ...");
	

	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	private EErroLeitura(String mensage) {
		this.mensage = mensage;
	}
	
}
