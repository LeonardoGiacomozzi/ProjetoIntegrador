package edu.sit.view.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.sit.controller.cadastro.ClienteController;
import edu.sit.controller.cadastro.FornecedorController;
import edu.sit.controller.cadastro.FuncionarioController;
import edu.sit.controller.cadastro.ProdutoController;
import edu.sit.erro.cadastro.CadastroException;

public class MenuCadastro {

	public static void menuCadastro() {
		
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\n**** CADASTROS TABACARIA ****");
		System.out.println("1 - Cadastrar Cliente");
		System.out.println("2 - Cadastrar Produtos");
		System.out.println("3 - Cadastrar Fornecedor");
		System.out.println("0 - Voltar");
		System.out.println("\nInforme a op��o desejada: \t");

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
			System.out.println("\n**** CADASTRAR CLIENTE ****");
			try {
				ClienteController.cadastro();
				System.out.println("\n");
				MenuPrincipal.menuGeral();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_CLIENTE);
			}
			break;

		case "2":
			System.out.println("\n**** CADASTRAR PRODUTO ****");
			try {
				ProdutoController.cadastro();
				MenuPrincipal.menuGeral();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_PRODUTO);
			}
			break;

		case "3": //Cadastro de Fornecedor: Est� comentando para n�o duplicar T�tulos no Console!
			try {
				FornecedorController.cadastro();
				MenuPrincipal.menuGeral();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_FORNECEDOR);
			}
			break;

		default:
			System.out.println("\nERRO - Escolha uma op��o v�lida!\n");
			MenuCadastro.menuCadastro();
			break;
		}
	}
}
