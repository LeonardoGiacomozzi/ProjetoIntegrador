package edu.sit.erro.notaFiscal;

public enum EErroNotaFiscal {
	
	ERRO_GRAVA_ARQUIVO("\nErro ao gerar o arquivo, verifique os dados informados e tente novamente...");
	
	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	
	private EErroNotaFiscal(String mensage) {
		this.mensage = mensage;
	}

}
