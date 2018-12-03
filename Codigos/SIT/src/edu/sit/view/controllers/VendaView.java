package edu.sit.view.controllers;

import edu.sit.DataObject.ProdutoQuantidade;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.VendaDao;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Venda;

public class VendaView {
	
	public static boolean visualizar() throws VisualizacaoException {

		try {
			for (Venda venda : new VendaDao().consultaTodos()) {
				System.out.println("#" + venda.getId() + " ----------- " + venda.getValor() + " ----------- "
						+ venda.getClienteId());
				System.out.println("-------------------------Produtos-------------------------");
				for (ProdutoQuantidade produto : venda.getProdutos()) {
					System.out.println("\t\t#" + produto.getItensPedido().getId() + " ----------- " + produto.getItensPedido().getNome());
				}
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_VENDAS);
		}
	}
	
}
