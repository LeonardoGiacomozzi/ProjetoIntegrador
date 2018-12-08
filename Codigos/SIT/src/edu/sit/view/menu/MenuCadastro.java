package edu.sit.view.menu;



import edu.sit.controller.cadastro.CategoriaController;
import edu.sit.controller.cadastro.ClienteController;
import edu.sit.controller.cadastro.FornecedorController;
import edu.sit.controller.cadastro.ProdutoController;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.uteis.Leitor;

public class MenuCadastro {

	public static void menuCadastro() {
		
		System.out.println("\n**** CADASTROS TABACARIA ****");
		System.out.println("1 - Cadastrar Cliente");
		System.out.println("2 - Cadastrar Produtos");
		System.out.println("3 - Cadastrar Fornecedor");
		System.out.println("4 - Cadastrar Categoria");
		System.out.println("0 - Voltar");
		System.out.print("\nInforme a opção desejada: \t");

		Integer escolha = Integer.MAX_VALUE;
		try {
			escolha = Leitor.leInteger();
		} catch (LeituraException e1) {
	
		}

		switch (escolha) {
		case 0:
			System.out.print("\n");
			MenuPrincipal.menuGeral();
			break;
		
		case 1:
			System.out.println("\n**** CADASTRAR CLIENTE ****");
			try {
				ClienteController.cadastro();
				System.out.println("\n");
				MenuCadastro.menuCadastro();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_CLIENTE);
			}
			break;

		case 2:
			System.out.println("\n**** CADASTRAR PRODUTO ****");
			try {
				ProdutoController.cadastro();
				MenuCadastro.menuCadastro();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_PRODUTO);
			}
			break;

		case 3: //Cadastro de Fornecedor: Está comentando para não duplicar Títulos no Console!
			try {
				FornecedorController.cadastro();
				MenuCadastro.menuCadastro();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_FORNECEDOR);
			}
			break;
		case 4:
			try {
				CategoriaController.cadastro();
				MenuCadastro.menuCadastro();
			} catch (CadastroException e) {
				System.out.println(EMensagensErroCad.ERRO_ESCRITA);
			}
			break;
		default:
			System.out.print("\nERRO - Escolha uma opção válida!\n");
			MenuCadastro.menuCadastro();
			break;
		}
	}
}
