package edu.sit.view.controllers;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Fornecedor;

public class FornecedorView {
	public static boolean visualizar() throws VisualizacaoException {

		try {
			for (Fornecedor fornecedor : new FornecedorDao().consultaTodos()) {
				System.out.println("#" + fornecedor.getId() + " ----------- " + fornecedor.getNome()+ " ----------- " + fornecedor.getCNPJ());
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_FORNECEDORES);
		}
	}
}
