package edu.sit.view.controllers;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.controller.cadastro.ProdutoController;
import edu.sit.erro.controller.ControllerException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Produto;
import edu.sit.uteis.Leitor;

public class ProdutoView {

	public static boolean visualizar() throws VisualizacaoException {
		try {
			System.out.println("\n**** LISTA DE PRODUTOS ****\n");
			System.out.println(String.format("%-10s", "Codigo") + String.format("%-19s", "Nome")
					+ String.format("%-13s", "Fornecedor") + String.format("%-13s", "Categoria")
					+ String.format("%-8s", "Valor") + String.format("%-6s", "Quantidade"));
			for (Produto produto : new ProdutoDao().consultaTodosCompleto()) {
				System.out.println(
						String.format("%-10s", "[" + produto.getId() + "]") + String.format("%-20s", produto.getNome())
								+ String.format("%-13s", produto.getFornecedor().getNome())
								+ String.format("%-13s", produto.getCategoria().getNome())
								+ String.format("%-10s", produto.getValorUnitario()) + produto.getQuantidade());
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_PRODUTOS);
		}
	}

	public static boolean reporEstoque() {
		Integer cod = 0;
		Integer qtd = null;
		while (cod == 0) {
			try {
							
				while (cod == 0) {
					try {
						ProdutoView.visualizar();
						try {
							System.out.print("\nInforme o código do produto que deseja repor: \t");
							cod = Leitor.leInteger();
							Produto produto = new ProdutoDao().consulta(cod);
							System.out.print("\nProduto: [" + produto.getNome() +
							                 "]\nDisponível: [" + produto.getQuantidade() + 
							                 "]\nInforme a quantidade que deseja adicionar: \t");
							qtd = Leitor.leInteger();
						} catch (LeituraException e) {
							System.out.println(e.getMessage());
						}
						try {
							ProdutoController.reporEstoque(cod, qtd);
							
							ProdutoView.visualizar();
							System.out.print("\n");
							return true;
						} catch (ControllerException e) {
							System.out.println(
									e.getMessage() + "\n\tNão foi possível adicionar a quantidade ao produto!");
						}
					} catch (VisualizacaoException e) {
						System.out.println(e.getMessage() + "\n\tNão foi possível listar os produtos!");
					}
				}
			} catch (DaoException | ConexaoException e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}
}
