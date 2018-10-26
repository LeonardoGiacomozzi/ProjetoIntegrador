package edu.sit.erro.notaFiscal;

public enum EErroNotaFiscal {
	
	ERRO_GRAVA_ARQUIVO("Erro ao Gerar um arquivo para a nota fiscal, verifique os dados informados e tente novamente");

	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	
	private EErroNotaFiscal(String mensage) {
		
	}

}
