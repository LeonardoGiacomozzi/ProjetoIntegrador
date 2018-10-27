package edu.sit.view.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuPrincipal {

	public static void menuGeral() {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" *** Menu Tabacaria *** ");
		System.out.println("1 - Cadastrar");
		System.out.println("2 - Vendas");
		System.out.println("3 - Gerenciar");
		System.out.println("4 - Configurações");

		String escolha = "";

		try {
			escolha = teclado.readLine();
		} catch (IOException e) {
			System.out.println("TNC");
			
		}

		switch (escolha) {
		case "1":
			MenuCadastro.menuCadastro();
			break;
			
		case "2":
			System.out.println("VENDAS");
			break;
			
		case "3":
			System.out.println("Gerenciar");
			break;
			
		case "4":
			System.out.println("Configurações");
			break;
			
		default:
			System.out.println("Sair");
			break;
		}

	}
}
