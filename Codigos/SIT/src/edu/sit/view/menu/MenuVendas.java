package edu.sit.view.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuVendas {
	
	public static void menuVendas() {

		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("**** VENDAS ****");
		System.out.println("1 - Cadastrar Cliente");
		System.out.println("2 - Cadastrar Funcion�rio");
		System.out.println("3 - Cadastrar Produtos");
		System.out.println("4 - Cadastrar Fornecedor");
		System.out.println("0 - Voltar");

		String escolha = "";
		try {
			escolha = teclado.readLine();
		} catch (IOException e) {
			System.out.println(EMensagensErroCad.ERRO_ESCRITA);
		}

		switch (escolha) {
		case "0":
			MenuPrincipal.menuGeral();

		case "1":
			System.out.println("*** CADASTRAR CLIENTE ***");
			try {
				Cadastros.montaMenuCadastroCliente();
			} catch (TabacariaException e) {
				System.out.println(EMensagensErroCad.ERRO_CLIENTE);
			}
			break;

		case "2":
			System.out.println("*** CADASTRAR FUNCION�RIO ***");
			try {
				Cadastros.montaMenuFuncionario();
			} catch (TabacariaException e) {
				System.out.println(EMensagensErroCad.ERRO_FUNCIONARIO);
			}
			break;

		case "3":
			System.out.println("*** CADASTRAR PRODUTO ***");
			try {
				Cadastros.montaMenuProduto();
			} catch (TabacariaException e) {
				System.out.println(EMensagensErroCad.ERRO_PRODUTO);
			}
			break;

		case "4":
			System.out.println("*** CADASTRO FORNECEDOR ***");
			try {
				Cadastros.montaMenuFornecedor();
			} catch (TabacariaException e) {
				System.out.println(EMensagensErroCad.ERRO_FORNECEDOR);
			}
			break;

		case "5":
			System.out.println("*** CADASTRO VENDAS ***");
			try {
				Cadastros.montaMenuVendas();
			} catch (TabacariaException e) {
				System.out.println(EMensagensErroCad.ERRO_VENDAS);
			}
			break;

		default:
			System.out.println("ERRO - Escolha uma op��o v�lida!");
			MenuCadastro.menuCadastro();
			break;
		}
	}
}
