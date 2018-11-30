package edu.sit.view.controllers;

import edu.sit.controller.cadastro.UsuarioController;
import edu.sit.model.Usuario;
import edu.sit.uteis.cadastro.UtilCadastro;

public class UsuarioView {
	
	public static boolean login() {
		System.out.println("Acesso restrito");
		String login = UtilCadastro.pedeNome("\tLogin\t:");
		String senha = UtilCadastro.pedeNome("\tSenha\t:");
		return UsuarioController.validaLogin(Usuario.criaUsuario(login, senha));
		
		
	}
}
