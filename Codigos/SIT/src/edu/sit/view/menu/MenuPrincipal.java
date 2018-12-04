package edu.sit.view.menu;


import edu.sit.controller.cadastro.ClienteController;
import edu.sit.controller.cadastro.VendaController;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.uteis.Leitor;
import edu.sit.view.controllers.ClienteView;
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
			try {
				ClienteView.visualizar();
				System.out.print("\nSelecione qual Cliente deseja alterar: \t");
				try {
					Integer codigo = Leitor.leInteger();
					ClienteController.editar(codigo);
					MenuPrincipal.menuGeral();
				} catch (EdicaoException | LeituraException e) {
					System.out.println(e.getMessage());
				}
			} catch (VisualizacaoException a) {
				System.out.println(a.getMessage());
			}
			break;
		case 4:
			if( UsuarioView.login()){
				MenuGerente.menusGerente();
			}else {
				System.out.println("\nInformações INVÁLIDAS!\n");
				MenuPrincipal.menuGeral();
			}
			break;
		case 5:
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
