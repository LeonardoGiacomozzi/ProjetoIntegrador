package edu.sit.view.controllers;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.UsuarioDao;
import edu.sit.controller.cadastro.UsuarioController;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Usuario;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;

public class UsuarioView {
	
	public static boolean login() {
		System.out.println("\nAcesso restrito!");
		String login = UtilCadastro.pedeNome("\nLogin: \t");
		String senha = UtilCadastro.pedeNome("Senha: \t");
		return UsuarioController.validaLogin(new Usuario(login, senha));
		
		
	}

	public static boolean visualizar() throws VisualizacaoException {
		
		try {
			System.out.println("\n**** LISTA DE USUÁRIOS ****");
			System.out.println(String.format("%-16s", "\nCódigo") + 
							   String.format("%-30s", "Login"));
			for (Usuario usuario : new UsuarioDao().consultaTodos()) {
				System.out.println(String.format("%-15s", "[" + usuario.getId() + "]") +
								   String.format("%-30s", usuario.getLogin()));
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_USUARIOS);
		}
	}
	
	public static boolean exclui() {
		try {
			Integer op = null;
			
			while(op == null || (new UsuarioDao().consulta(op) == null && op != 0) || op == 1) {
				try {
					
					UsuarioView.visualizar();
					System.out.print("\n[0] - Sair\n");
					System.out.print("\nInforme o Usuário que deseja excluir: \t");
					op = Leitor.leInteger();
					
				} catch (LeituraException e) {
					System.out.println(e.getMessage());
				}
			}
			if (op != 0) {
			System.out.println(UsuarioController.exclui(op) ? "\nUsuário excluído com SUCESSO!" : "\nFalha");
			}
			return true;
		} catch (VisualizacaoException | DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
