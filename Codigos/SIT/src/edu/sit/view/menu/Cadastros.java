package edu.sit.view.menu;

import edu.sit.uteis.Leitor;

public class Cadastros {

	public static void montaMenuCadastroCliente() throws TabacariaException {
		try {
			System.out.println("CNPJ: \t");
			Leitor.leCnpj();
		}
		
		catch (Exception e) {
			// TODO: handle exception
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
