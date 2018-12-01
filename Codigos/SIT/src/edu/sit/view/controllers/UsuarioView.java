package edu.sit.view.controllers;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.UsuarioDao;
import edu.sit.controller.cadastro.UsuarioController;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Usuario;
import edu.sit.uteis.cadastro.UtilCadastro;

public class UsuarioView {
	
	public static boolean login() {
		System.out.println("Acesso restrito");
		String login = UtilCadastro.pedeNome("\tLogin\t:");
		String senha = UtilCadastro.pedeNome("\tSenha\t:");
		return UsuarioController.validaLogin(Usuario.criaUsuario(login, senha));
		
		
	}

	public static boolean visualizar() throws VisualizacaoException {
		
		try {
			System.out.println(String.format("%-10s", "Código") + 
							   String.format("%-30s", "Login"));
			for (Usuario usuario : new UsuarioDao().consultaTodos()) {
				System.out.println(String.format("%-10s", "[" + usuario.getId() + "]") +
								   String.format("%-30s", usuario.getLogin()));
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_USUARIOS);
		}
	}
}
