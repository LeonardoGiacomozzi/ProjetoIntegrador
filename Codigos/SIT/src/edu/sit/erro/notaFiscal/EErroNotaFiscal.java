package edu.sit.erro.notaFiscal;

public enum EErroNotaFiscal {
	
	ERRO_GRAVA_ARQUIVO("Erro ao gerar um arquivo para a nota fiscal, verifique os dados informados e tente novamente"),
	ERRO_GRAVAR_BINARIO("Erro ao gerar um arquivo binario para a nota fiscal, verifique os dados informados e tente novamente");
	

	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	
	private EErroNotaFiscal(String mensage) {
		this.mensage = mensage;
	}

}
