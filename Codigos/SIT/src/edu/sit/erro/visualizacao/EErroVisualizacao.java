package edu.sit.erro.visualizacao;

public enum EErroVisualizacao {
	ERRO_BUSCA_CATEGORIAS("Erro ao buscar as Caracteristicas no banco"),
	ERRO_BUSCA_CLIENTES("Erro ao buscar os Clientes no banco"),
	ERRO_BUSCA_CONTATOS("Erro ao buscar os Contatos no banco"),
	ERRO_BUSCA_FORNECEDORES("Erro ao buscar os Fornecedores no banco"),
	ERRO_BUSCA_FUNCIONARIOS("Erro ao buscar os Funcionarios no banco"),
	ERRO_BUSCA_PRODUTOS("Erro ao buscar os Produtos no banco"),
	ERRO_BUSCA_VENDAS("Erro ao buscar as Vendas no banco"),
	ERRO_BUSCA_USUARIOS("Erro ao buscar os Úsuarios no banco");

	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	private EErroVisualizacao(String mensagem) {
		this.mensagem = mensagem;
	}
}


