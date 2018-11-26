package edu.sit.view.menu;

import edu.sit.uteis.Leitor;

public class Cadastros {

	public static void montaMenuCadastroCliente() throws TabacariaException {
		try {
			System.out.print("Nome: \t\t\t");
			Leitor.leString();
			System.out.print("CPF: \t\t");
			Leitor.leCpf();
			System.out.print("Data de Nascimento: \t");
			Leitor.leData();
			System.out.print("Endereço: \t\t");
			Leitor.leString();
		} catch (Exception e) {
			System.out.println(EMensagensErroCad.ERRO_ESCRITA);
		}
		
	}

	public static void montaMenuFuncionario() throws TabacariaException {

	}

	public static void montaMenuProduto() throws TabacariaException {

	}

	public static void montaMenuFornecedor() throws TabacariaException {

	}

	public static void montaMenuVendas() throws TabacariaException {

	}

}
