package edu.sit.view.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.sit.controller.cadastro.ClienteController;
import edu.sit.controller.cadastro.FornecedorController;
import edu.sit.controller.cadastro.FuncionarioController;
import edu.sit.controller.cadastro.ProdutoController;
import edu.sit.controller.cadastro.VendaController;
import edu.sit.erro.cadastro.CadastroException;

public class MenuCadastro {

	public static void menuCadastro() {
		
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\n**** CADASTROS TABACARIA ****");
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
		case "0":
			System.out.print("\n");
			MenuPrincipal.menuGeral();
			break;
		
		case "1":
			System.out.println("\n*** CADASTRAR CLIENTE ***");
			try {
				ClienteController.cadastro();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_CLIENTE);
			}
			break;

		case "2":
			System.out.println("\n*** CADASTRAR FUNCIONÁRIO ***");
			try {
				FuncionarioController.cadastro();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_FUNCIONARIO);
			}
			break;

		case "3":
			System.out.println("\n*** CADASTRAR PRODUTO ***");
			try {
				ProdutoController.cadastro();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_PRODUTO);
			}
			break;

		case "4": //Cadastro de Fornecedor: Está comentando para não duplicar Títulos no Console!
			try {
				FornecedorController.cadastro();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_FORNECEDOR);
			}
			break;

		case "5":
			System.out.println("\n*** CADASTRO VENDAS ***");
			try {
				VendaController.efetuaVenda();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_VENDAS);
			}
			break;

		default:
			System.out.println("\nERRO - Escolha uma opção válida!\n");
			MenuCadastro.menuCadastro();
			break;
		}
	}
}
