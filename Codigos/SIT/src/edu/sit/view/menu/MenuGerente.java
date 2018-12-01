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
import edu.sit.view.controllers.UsuarioView;

public class MenuGerente {

	public static void menusGerente() {
		System.out.println("1-Configurações");
		System.out.println("2-Cadastro especifico");
		System.out.println("3-Gerenciamento especifico");
		System.out.println("4-Gerenciar Vendas");
		System.out.println("0-Voltar");
		System.out.println("\nInforem a opção desejada");
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
			MenuPrincipal.menuGeral();
			break;
		default:
			break;
		}
	}

	private static void menuCadastro() {
		System.out.println("1-Cadastrar funcionario");
		System.out.println("2-Cadastrar úsuario");
		System.out.println("0-Voltar");
		System.out.println("\nInforem a opção desejada");
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
			System.out.println("Informe uma opção valida");
			MenuGerente.menuCadastro();
			break;
		}
	}

	private static void menuGerenciamento() {

		System.out.println("1-Editar funcionario");
		System.out.println("2-Editar usuario");
		System.out.println("0-Voltar");
		System.out.println("\nInforem a opção desejada");
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
				System.out.println("Selecionae qual usuario deseja trocar a senha");
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
			break;
		}

	}

	private static void menuVendas() {

	}

	private static void menuFuncionario() {
		System.out.println("1-Buscar por CPF");
		System.out.println("2-Listar todos");
		System.out.println("0-Voltar");
		System.out.println("\nInforem a opção desejada");
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
			MenuGerente.menusGerente();
			break;
		case 0:
			MenuGerente.menusGerente();
			break;
		default:
			System.out.println("Informe uma opção valida");
			MenuGerente.menuFuncionario();
			break;

		}
	}

}
