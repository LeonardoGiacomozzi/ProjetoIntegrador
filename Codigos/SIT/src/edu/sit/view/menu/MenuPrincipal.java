package edu.sit.view.menu;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.controller.cadastro.ClienteController;
import edu.sit.controller.cadastro.VendaController;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.uteis.Leitor;
import edu.sit.view.controllers.ClienteView;
import edu.sit.view.controllers.ProdutoView;
import edu.sit.view.controllers.UsuarioView;

public class MenuPrincipal {

	public static void menuGeral() {
		System.out.println("**** MENU TABACARIA ****");
		System.out.println("1 - Efetuar Cadastros");
		System.out.println("2 - Efetuar Vendas");
		System.out.println("3 - Editar Cliente");
		System.out.println("4 - Área do Gerente");
		System.out.println("5 - Repor Estoque");
		System.out.println("0 - Sair");

		Integer escolha = Integer.MAX_VALUE;
		System.out.print("\nInforme a opção desejada: \t");
		try {
			escolha = Leitor.leInteger();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		switch (escolha) {
		case 1: // Cadastros
			MenuCadastro.menuCadastro();
			break;

		case 2:
			try {
				VendaController.efetuaVenda();
				MenuPrincipal.menuGeral();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			Integer codigo = null;
			while (codigo == null) {
				try {
					ClienteView.visualizar();
				} catch (VisualizacaoException e3) {
					System.out.println(e3.getMessage());
				}
				System.out.print("\nSelecione qual Cliente deseja alterar: \t");
				try {
					codigo = Leitor.leInteger();
				} catch (LeituraException e2) {
					System.out.println(e2.getMessage());
				}
				try {
					while (new ClienteDao().consulta(codigo) == null) {
						System.out.println("Cliente não encontrado");
						System.out.println("Informe o Cliente novamente");
						ClienteView.visualizar();
						try {
							codigo = Leitor.leInteger();
						} catch (LeituraException e) {
							System.out.println(e.getMessage());
						}
					}
				} catch (DaoException | ConexaoException e) {
					System.out.println(e.getMessage());
				} catch (VisualizacaoException e1) {
					System.out.println(e1.getMessage());
				}
			}
			try {
				ClienteController.editar(codigo);
			} catch (EdicaoException e) {
				System.out.println(e.getMessage());
			}
			MenuPrincipal.menuGeral();

			break;
		case 4:
			if (UsuarioView.login()) {
				MenuGerente.menusGerente();
			} else {
				System.out.println("\nInformações INVÁLIDAS!\n");
				MenuPrincipal.menuGeral();
			}
			break;
		case 5:
			Integer auxiliar = 1;
			while (auxiliar != 0) {
				ProdutoView.reporEstoque();
				System.out.println(String.format("%-10s", "[1]") + "Inserir");
				System.out.println(String.format("%-10s", "[0]") + "Sair");
				System.out.print("\nInforme a opção desejada: \t");

				try {
					auxiliar = Leitor.leInteger();
				} catch (LeituraException e) {
					System.out.println(e.getMessage());
				}

				break;
			}
			MenuPrincipal.menuGeral();
			break;
		case 0:
			System.out.println("\n\n\n****                   VOCÊ SAIU                   ****");
			System.out.println("****       OBRIGADO POR USAR O SISTEMA SIT!!       ****");
			break;
		default:
			System.out.println("\nERRO - Escolha uma opção válida!\n");
			MenuPrincipal.menuGeral();
			break;
		}

	}
}
