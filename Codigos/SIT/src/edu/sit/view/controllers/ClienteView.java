package edu.sit.view.controllers;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Cliente;

public class ClienteView {

	
	public static boolean visualizar() throws VisualizacaoException {

		try {
			System.out.println("\n**** LISTA DE CLIENTES ****\n");
			System.out.println(String.format("%-10s", "Código") + 
							   String.format("%-30s", "Nome") + 
							   "CPF");
			for (Cliente cliente : new ClienteDao().consultaTodos()) {
				System.out.println(String.format("%-10s", "[" + cliente.getId() + "]") +
								   String.format("%-30s", cliente.getNome())+
								   String.format("%-10s", cliente.getCpf()));
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_CLIENTES);
		}
	}
}
