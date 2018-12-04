package edu.sit.view.controllers;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.CategoriaDao;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Categoria;

public class CategoriaView {

	public static boolean visualizar() throws VisualizacaoException {

		try {
			System.out.println("\n**** LISTA DE CATEGORIAS ****\n");
			System.out.println(String.format("%-10s", "Código") + 
							   String.format("%-30s", "Nome"));
			for (Categoria categoria : new CategoriaDao().consultaTodos()) {
				System.out.println(String.format("%-10s", "[" + categoria.getId() + "]" ) + categoria.getNome());
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_CATEGORIAS);
		}
	}
}
