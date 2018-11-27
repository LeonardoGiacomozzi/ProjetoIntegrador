package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Usuario;
import edu.sit.uteis.cadastro.UtilCadastro;

public class UsuarioController {
	
	
	public static boolean cadastro() throws CadastroException {
		
		String login = null;
		String senha = null;
		System.out.println("*********CADASTRO DE USUARIO*********");
		login = UtilCadastro.pedeNome();
		senha = UtilCadastro.pedeSenha();
		
		try {
			System.out.println(new UsuarioDao().insere( Usuario.criaUsuario(login, senha))?"Usuario cadastrado com SUCESSO!!": "FATAL_ERROR");
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new CadastroException(EErroCadastro.ERRO_CADASTRO_USUARIO);
		}
		
		
	}
	
}
