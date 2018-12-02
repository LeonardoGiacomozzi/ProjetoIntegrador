package edu.sit.erro.leitura;

public enum EErroLeitura {
	
	ERRO_LER_STRING("Informe um valor válido..."),
	ERRO_LER_INTEGER("\nInforme um valor inteiro e válido..."),
	ERRO_LER_DOUBLE("Informe um valor do tipo double - (Ex: 10.5)..."), 
	ERRO_LER_DATA ("Informe uma data no formato - (dia/mês/ano)..."),
	ERRO_LER_CPF ("Informe um CPF no formato - 000.000.000-00 ..."),
	ERRO_LER_CNPJ("Informe um CNPJ no formato - 00.000.000/0001-00 ..."),
	ERRO_LER_FONE("Informe um Telefone no formato - (00)1111-1111 ..."),
	ERRO_LER_EMAIL("Informe um Email no formato correto - exemplo1@hotmail.com ...");
	

	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	private EErroLeitura(String mensage) {
		this.mensage = mensage;
	}
	
}
