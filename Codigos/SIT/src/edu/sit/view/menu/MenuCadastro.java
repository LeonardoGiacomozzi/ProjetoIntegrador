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
		case "0":
			MenuPrincipal.menuGeral();
		
		case "1":
			System.out.println("*** CADASTRAR CLIENTE ***");
			try {
				ClienteController.cadastro();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_CLIENTE);
			}
			break;

		case "2":
			System.out.println("*** CADASTRAR FUNCIONÁRIO ***");
			try {
				FuncionarioController.cadastro();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_FUNCIONARIO);
			}
			break;

		case "3":
			System.out.println("*** CADASTRAR PRODUTO ***");
			try {
				ProdutoController.cadastro();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_PRODUTO);
			}
			break;

		case "4":
			System.out.println("*** CADASTRO FORNECEDOR ***");
			try {
				FornecedorController.cadastro();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_FORNECEDOR);
			}
			break;

		case "5":
			System.out.println("*** CADASTRO VENDAS ***");
			try {
				VendaController.efetuaVenda();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_VENDAS);
			}
			break;

		default:
			System.out.println("ERRO - Escolha uma opção válida!");
			MenuCadastro.menuCadastro();
			break;
		}
	}
}
