package edu.sit.view.controllers;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Produto;

public class ProdutoView {
	
	public static boolean visualizar() throws VisualizacaoException {
		try {
			System.out.println("\n**** LISTA DE PRODUTOS ****\n");
			System.out.println(String.format("%-10s", "Codigo") + String.format("%-19s", "Nome")
					+ String.format("%-13s", "Fornecedor") + String.format("%-13s", "Categoria")
					+ String.format("%-8s", "Valor") + String.format("%-6s", "Quantidade"));
			for (Produto produto : new ProdutoDao().consultaTodosCompleto()) {
				System.out.println(String.format("%-10s", "[" + produto.getId() + "]")
						+ String.format("%-20s", produto.getNome())
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
}
