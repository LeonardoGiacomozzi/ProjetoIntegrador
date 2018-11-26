package edu.sit.view.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuCadastro {

	public static void menuCadastro() {
		
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("**** Cadastros Tabacaria ****");
		System.out.println("1 - Cadastrar cliente");
		System.out.println("2 - Cadastrar funcionário");
		System.out.println("3 - Cadastrar produto");
		System.out.println("4 - Cadastrar fornecedor");
		System.out.println("5 - Cadastro de vendas");

		String escolha = "";
		try {
			escolha = teclado.readLine();
		} catch (IOException e) {
			System.out.println("TNC");
			
		}

		switch (escolha) {
		case "1":
			System.out.println("Cadastrar Cliente");
			try {
				Cadastros.montaMenuCadastroCliente();
			} catch (TabacariaException e) {
				System.out.println(EMensagensErroCad.ERRO_CLIENTE);
			}
			break;

		case "2":
			System.out.println("Cadastar Funcionario");
			try {
				Cadastros.montaMenuFuncionario(); 
			} catch (TabacariaException e) {
				System.out.println(EMensagensErroCad.ERRO_FUNCIONARIO);
			}
			break;

		case "3":
			System.out.println("Cadastrar Produto");
			try {
				Cadastros.montaMenuProduto();
			} catch (TabacariaException e) {
				System.out.println(EMensagensErroCad.ERRO_PRODUTO);
			}
			break;

		case "4":
			System.out.println("Cadastro Fornecedor");
			try {
				Cadastros.montaMenuFornecedor();
			} catch (TabacariaException e) {
				System.out.println(EMensagensErroCad.ERRO_FORNECEDOR);
			}
			break;

		case "5":
			System.out.println("Cadastrar Vendas");
			try {
				Cadastros.montaMenuVendas();
			} catch (TabacariaException e) {
				System.out.println(EMensagensErroCad.ERRO_VENDAS);
			}
			break;

		default:
			System.out.println("Sair");
			break;
		}
		
	}
}
