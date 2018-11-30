package edu.sit.view.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.sit.controller.cadastro.VendaController;

public class MenuPrincipal {

	public static void menuGeral() {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("**** MENU TABACARIA ****");
		System.out.println("1 - Efetuar Cadastros");
		System.out.println("2 - Efetuar Vendas");
		System.out.println("3 - Gerenciamento");
		System.out.println("4 - Area do Gerenete");
		System.out.println("0 - Sair");

		String escolha = "";

		try {
			escolha = teclado.readLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			
		}

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
			System.out.println("*** GERENCIAMENTO ***");
			break;
			
		case "4":
			System.out.println("*** CONFIGURAÇÕES ***");
			break;
			
		default:
			System.out.println("\n\n\n****                   VOCÊ SAIU                   ****");
			System.out.println("****       OBRIGADO POR USAR O SISTEMA SIT!!       ****");
			break;
		}

	}
}
