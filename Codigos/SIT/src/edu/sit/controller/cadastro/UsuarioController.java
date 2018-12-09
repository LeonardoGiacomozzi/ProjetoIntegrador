package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.UsuarioDao;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Usuario;
import edu.sit.uteis.cadastro.UtilCadastro;

public class UsuarioController {

	public static boolean cadastro() throws CadastroException {

		String login = null;
		String senha = null;
		System.out.println("\n**** CADASTRO DE USUÁRIO ****");
		try {
			do {
				login = UtilCadastro.pedeNome("Login: \t");
			}while(new UsuarioDao().consulta(login)!=null);
		} catch (DaoException | ConexaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		senha = UtilCadastro.pedeSenhaFrase("Senha: \t");

		try {
			System.out.println(
					new UsuarioDao().insere(Usuario.criaUsuario(login, senha)) ? "\nUsuário cadastrado com SUCESSO!" : "\nFalha");
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new CadastroException(EErroCadastro.ERRO_CADASTRO_USUARIO);
		}

	}

	public static boolean validaLogin(Usuario entrando) {

		try {
			Usuario doBanco = new UsuarioDao().consulta(entrando.getLogin());
			if (doBanco == null) {
				return false;
			}
			if (doBanco.equals(entrando)) {
				return true;
			}
			return false;
		} catch (DaoException e) {
			System.out.println(e.getMessage() + "\n Usuário não encontrado\n");
			return false;
		} catch (ConexaoException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean trocaSenha(Usuario usuario) {
		String senha1 = null;
		String senha2 = "zz";
		senha1 = UtilCadastro.pedeSenhaFrase("\nSenha mestre do sistema: \t");
		
		if ("@ADM".equals(senha1)) {
			while (!senha1.equals(senha2)) {
				System.out.print("Informe nova senha: \t");
				senha1 = UtilCadastro.pedeSenha();
				System.out.print("Informe a senha novamente: \t");
				senha2 = UtilCadastro.pedeSenha();
			}
			try {
				usuario.setSenha(senha2);
				new UsuarioDao().altera(usuario);
				System.out.print("\nSenha alterada com SUCESSO!\n");
			} catch (DaoException | ConexaoException e) {
				System.out.println(e.getMessage());
			}
			return true;
		}
		System.out.print("\nSenha Incorreta! Você foi redirecionado para o Gerenciamento!\n");
		return false;
	}

	public static boolean exclui(Integer codigo) {
		 
		try {
			new UsuarioDao().exclui(codigo);
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
