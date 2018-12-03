package edu.sit.view.menu;


import edu.sit.controller.cadastro.VendaController;
import edu.sit.uteis.Leitor;
import edu.sit.view.controllers.UsuarioView;

public class MenuPrincipal {

	public static void menuGeral() {
		System.out.println("**** MENU TABACARIA ****");
		System.out.println("1 - Efetuar Cadastros");
		System.out.println("2 - Efetuar Vendas");
		System.out.println("3 - Área do Gerente");
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
			if( UsuarioView.login()){
				MenuGerente.menusGerente();
			}else {
				System.out.println("\nInformações INVÁLIDAS!\n");
				MenuPrincipal.menuGeral();
			}
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
