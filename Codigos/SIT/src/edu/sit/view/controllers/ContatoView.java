package edu.sit.view.controllers;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Contato;

public class ContatoView {

	public static boolean visualizar() throws VisualizacaoException {

		try {
			for (Contato contato : new ContatoDao().consultaTodos()) {
				System.out.println("#" + contato.getId() + " ----------- " + contato.getTelefone()+ " ----------- " + contato.getEmail());
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_CONTATOS);
		}
	}
}
