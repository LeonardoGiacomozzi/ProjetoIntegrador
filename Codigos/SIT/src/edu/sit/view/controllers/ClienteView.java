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

	
	public static void exibeCliente(Cliente cliente) {
		System.out.println("************* DADOS DO CLIENTE***************");
		System.out.println("Nome\t"+cliente.getNome());
		System.out.println("CPF\t"+cliente.getCpf());
		System.out.println("Email\t"+cliente.getContato().getEmail()+"--------- Telefone\t"+cliente.getContato().getTelefone());
		System.out.println("Endereço\t"+cliente.getEndereco());
		System.out.println("Data de Nascimento\t"+cliente.getDataDeNascimento());
	
	}

}
