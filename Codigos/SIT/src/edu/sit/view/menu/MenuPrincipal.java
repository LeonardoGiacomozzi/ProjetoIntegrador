package edu.sit.view.menu;


import edu.sit.controller.cadastro.VendaController;
import edu.sit.uteis.cadastro.UtilCadastro;
import edu.sit.view.controllers.UsuarioView;

public class MenuPrincipal {

	public static void menuGeral() {
		System.out.println("**** MENU TABACARIA ****");
		System.out.println("1 - Efetuar Cadastros");
		System.out.println("2 - Efetuar Vendas");
		System.out.println("3 - Gerenciamento");
		System.out.println("4 - Área do Gerente");
		System.out.println("0 - Sair");

		String escolha = UtilCadastro.pedeNome("\nInforme a opção desejada: \t");

		switch (escolha) {
		case "1": // Cadastros
			MenuCadastro.menuCadastro();
			break;
			
		case "2":
			try {
				VendaController.efetuaVenda();
				MenuPrincipal.menuGeral();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
			
		case "3":
			System.out.println("**** GERENCIAMENTO ****\n");
			MenuPrincipal.menuGeral();
			break;
			
		case "4":
			if( UsuarioView.login()){
				MenuGerente.menusGerente();
			}else {
				System.out.println("VOCÊ NÃO TEM ACESSO A ESSA PARTE DO SISTEMA!!!");
			}
			break;
		case "0":
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
