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
			System.out.println("\n**** LISTA DE FORNECEDOR ****\n");
			System.out.println(String.format("%-15s", "Código") + String.format("%-20s", "Nome") + "CNPJ");
			for (Fornecedor fornecedor : new FornecedorDao().consultaTodos()) {
				System.out.println(String.format("%-15s", "[" + fornecedor.getId() + "]")
						+ String.format("%-20s", fornecedor.getNome()) + String.format("%-10s", fornecedor.getCNPJ()));
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_FORNECEDORES);
		}
	}

	public static void exibeFornecedor(Fornecedor fornecedor) {
		System.out.println("************* DADOS DO CLIENTE***************");
		System.out.println("Nome\t" + fornecedor.getNome());
		System.out.println("CNPJ\t" + fornecedor.getCNPJ());
		System.out.println("Email\t" + fornecedor.getContato().getEmail() + "--------- Telefone\t"
				+ fornecedor.getContato().getTelefone());
		System.out.println("Pessoa responsável\t" + fornecedor.getPessoaResponsavel());

	}

}
