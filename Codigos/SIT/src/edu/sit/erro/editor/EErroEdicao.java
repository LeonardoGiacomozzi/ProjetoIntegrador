package edu.sit.erro.editor;

public enum EErroEdicao {
	
	ERRO_EDICAO_CLIENTE("Erro ao editar o cliente"),
	ERRO_EDICAO_CONTATO("Erro ao editar o contato"),
	ERRO_EDICAO_FUNCIONARIO("Erro ao editar o funcionario"),
	ERRO_EDICAO_CATEGORIA("Erro ao editar a categoria"),
	ERRO_EDICAO_FORNECEDOR("Erro ao editar o fornecedor"),
	ERRO_EDICAO_PRODUTO("Erro ao editar o produto"),
	ERRO_BUSCA_CLIENTE("Erro ao buscar o cliente informado para edi��o"),
	ERRO_BUSCA_CONTATO("Erro ao editar o contato informado para edi��o"),
	ERRO_BUSCA_FUNCIONARIO("Erro ao editar o funcionario informado para edi��o"),
	ERRO_BUSCA_CATEGORIA("Erro ao editar a categoria informado para edi��o"),
	ERRO_BUSCA_FORNECEDOR("Erro ao editar o fornecedor informado para edi��o"),
	ERRO_BUSCA_PRODUTO("Erro ao editar o produto informado para edi��o");
	private String mensage;
	
	public String getMensage() {
		return mensage;
	}
	private EErroEdicao(String mensage) {
		// TODO Auto-generated constructor stub
	}

}
