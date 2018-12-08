package edu.sit.view.controllers;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.controller.cadastro.FuncionarioController;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Funcionario;
import edu.sit.uteis.Leitor;

public class FuncionarioView {

	public static boolean visualizar() throws VisualizacaoException {

		try {
			System.out.println("\n**** LISTA DE FUNCIONÁRIOS ****\n");
			System.out.println(String.format("%-10s", "Código") + String.format("%-30s", "Nome") + "CPF");
			for (Funcionario funcionario : new FuncionarioDao().consultaTodos()) {
				System.out.println(String.format("%-10s", "[" + funcionario.getId() + "]")
						+ String.format("%-30s", funcionario.getNome()) + String.format("%-10s", funcionario.getCpf()));
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_FUNCIONARIOS);
		}
	}

	public static boolean excluir() {
		try {
			FuncionarioView.visualizar();
			Integer op = null;
			do {
				try {
					System.out.print("\nInforme o funcionário que deseja excluir: \t");
					op = Leitor.leInteger();
				} catch (LeituraException e) {
					System.out.println(e.getMessage());
				}
			} while (op == null);
			return FuncionarioController.exclui(op);
		} catch (VisualizacaoException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
