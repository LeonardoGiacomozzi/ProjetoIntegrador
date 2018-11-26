package edu.sit.view.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuCadastro {

	public static void menuCadastro() {
		
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("**** CADASTROS TABACARIA ****");
		System.out.println("1 - Cadastrar Cliente");
		System.out.println("2 - Cadastrar Funcionário");
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
		case "1":
			System.out.println("*** CADASTRAR CLIENTE ***");
			try {
				Cadastros.montaMenuCadastroCliente();
			} catch (TabacariaException e) {
				System.out.println(EMensagensErroCad.ERRO_CLIENTE);
			}
			break;

		case "2":
			System.out.println("*** CADASTRAR FUNCIONÁRIO ***");
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
			MenuPrincipal.menuGeral();
			break;
		}
	}
}
