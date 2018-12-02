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
		System.out.println("\n**** CADASTRO DE USU�RIO ****");
		login = UtilCadastro.pedeNome("Login: \t");
		senha = UtilCadastro.pedeSenha();

		try {
			System.out.println(
					new UsuarioDao().insere(Usuario.criaUsuario(login, senha)) ? "\nUsu�rio cadastrado com SUCESSO!"
							: "FATAL_ERROR");
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new CadastroException(EErroCadastro.ERRO_CADASTRO_USUARIO);
		}

	}

	public static boolean validaLogin(Usuario entrando) {

		try {
			Usuario doBanco = new UsuarioDao().consulta(entrando.getLogin());
			if (doBanco.equals(entrando)) {
				return true;
			}
			System.out.println("Senha Inv�lida!");
			return false;
		} catch (DaoException e) {
			System.out.println(e.getMessage() + "\n Usu�rio n�o encontrado\n");
			return false;
		} catch (ConexaoException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean trocaSenha(Usuario usuario) {
		String senha1 = null;
		String senha2 = "zz";
		
		System.out.println("Senha mestre do sistema: \t");
		senha1 = UtilCadastro.pedeSenha();
		if ("@ADM".equals(senha1)) {
			while (!senha1.equals(senha2)) {
				System.out.println("Informe nova senha");
				senha1 = UtilCadastro.pedeSenha();
				System.out.println("Informe novamente");
				senha2 = UtilCadastro.pedeSenha();
				
			}
			return true;
		}
		return false;
	}

	

}
