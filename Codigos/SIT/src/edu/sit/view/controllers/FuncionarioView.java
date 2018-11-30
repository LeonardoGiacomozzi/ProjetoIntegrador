package edu.sit.view.controllers;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Funcionario;

public class FuncionarioView {

	public static boolean visualizar() throws VisualizacaoException {

		try {
			System.out.println(String.format("%-10s", "Código") + 
							   String.format("%-30s", "Nome") + 
							   "CPF");
			for (Funcionario funcionario : new FuncionarioDao().consultaTodos()) {
				System.out.println(String.format("%-10s", "[" + funcionario.getId() + "]") +  
								   String.format("%-30s", funcionario.getNome()) +
								   String.format("%-10s", funcionario.getCpf()));
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_FUNCIONARIOS);
		}
	}
	
}
