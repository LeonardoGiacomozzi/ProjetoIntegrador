package edu.sit.view.menu;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.bancodedados.dao.UsuarioDao;
import edu.sit.controller.cadastro.FuncionarioController;
import edu.sit.controller.cadastro.UsuarioController;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;
import edu.sit.view.configuracao.ConfiguracaoView;
import edu.sit.view.controllers.FuncionarioView;
import edu.sit.view.controllers.UsuarioView;

public class MenuGerente {

	public static void menusGerente() {
		System.out.println("\n**** GERENCIAMENTO ****");
		System.out.println("1 - Configura��es");
		System.out.println("2 - Cadastrar Funcion�rio/Usu�rio");
		System.out.println("3 - Editar Funcion�rio/Usu�rio");
		System.out.println("4 - Gerenciar Vendas");
		System.out.println("0 - Voltar");
		System.out.print("\nInforme a op��o desejada: \t");
		Integer op = null;
		while (op == null) {

			try {
				op = Leitor.leInteger();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}

		switch (op) {
		case 1:
			ConfiguracaoView.editar();
			MenuGerente.menusGerente();
			break;
		case 2:
			MenuGerente.menuCadastro();
			MenuGerente.menusGerente();
			break;
		case 3:
			MenuGerente.menuGerenciamento();
			MenuGerente.menusGerente();
			break;
		case 4:
			MenuGerente.menuVendas();
			MenuGerente.menusGerente();
			break;
		case 0:
			System.out.print("\n");
			MenuPrincipal.menuGeral();
			break;
		default:
			break;
		}
	}

	private static void menuCadastro() {
		System.out.println("\n1 - Cadastrar funcion�rio");
		System.out.println("2 - Cadastrar usu�rio");
		System.out.println("0 - Voltar");
		System.out.print("\nInforme a op��o desejada: \t");
		Integer op = null;
		while (op == null) {

			try {
				op = Leitor.leInteger();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}

		switch (op) {
		case 1:
			try {
				System.out.println(FuncionarioController.cadastro() ? "Cadastro efetuado com sucesso!!" : "");
			} catch (CadastroException e) {
				System.out.println(e.getMessage());
			}
			MenuGerente.menuCadastro();
			break;

		case 2:
			try {
				System.out.println(UsuarioController.cadastro() ? "Cadastro efetuado com sucesso!!" : "");
			} catch (CadastroException e) {
				System.out.println(e.getMessage());
			}
			MenuGerente.menuCadastro();
			break;

		case 0:
			MenuGerente.menusGerente();
			break;

		default:
			System.out.println("\nERRO - Escolha uma op��o v�lida!\n");
			MenuGerente.menuCadastro();
			break;
		}
	}

	private static void menuGerenciamento() {

		System.out.println("\n1 - Editar funcion�rio");
		System.out.println("2 - Editar usu�rio");
		System.out.println("0 - Voltar");
		System.out.print("\nInforme a op��o desejada: \t");
		Integer op = null;
		while (op == null) {

			try {
				op = Leitor.leInteger();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}

		switch (op) {
		case 1:
			MenuGerente.menuFuncionario();
			MenuGerente.menusGerente();
			break;
		case 2:
			try {
				UsuarioView.visualizar();
				System.out.print("\nSelecione qual usu�rio deseja trocar a senha: \t");
				UsuarioController.trocaSenha(new UsuarioDao().consulta(Leitor.leInteger()));

			} catch (VisualizacaoException | DaoException | ConexaoException | LeituraException e) {
				System.out.println(e.getMessage());
			}
			MenuGerente.menusGerente();
			break;
		case 0:
			MenuGerente.menusGerente();
			break;
		default:
			System.out.println("\nERRO - Escolha uma op��o v�lida!\n");
			break;
		}

	}

	private static void menuVendas() {
		System.out.println("1 - Ver vendas");
		
	}

	private static void menuFuncionario() {
		System.out.println("\n1 - Buscar por CPF");
		System.out.println("2 - Listar todos");
		System.out.println("0 - Voltar");
		System.out.print("\nInforme a op��o desejada: \t");
		Integer op = null;
		while (op == null) {

			try {
				op = Leitor.leInteger();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}

		switch (op) {
		case 1:
			try {
				FuncionarioController.editar(new FuncionarioDao().consultaPorCpf(UtilCadastro.pedeCpf()));
			} catch (EdicaoException | ConexaoException | DaoException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				
			}
			MenuGerente.menusGerente();
			break;
		case 2:
			try {
				FuncionarioView.visualizar();
				System.out.print("\nInforme o codigo do funcion�rio que deseja editar: \t");
				FuncionarioController.editar(Leitor.leInteger());
			} catch (VisualizacaoException | EdicaoException | LeituraException e) {
				System.out.println(e.getMessage());
			}
			
			MenuGerente.menusGerente();
			break;
		case 0:
			MenuGerente.menusGerente();
			break;
		default:
			System.out.println("\nERRO - Escolha uma op��o v�lida!\n");
			MenuGerente.menuFuncionario();
			break;

		}
	}

}
