package edu.sit.view.menu;

import edu.sit.erro.leitura.LeituraException;
import edu.sit.uteis.Leitor;
import edu.sit.view.configuracao.ConfiguracaoView;

public class MenuGerente {

	public static void menusGerente() {
		System.out.println("1-Configurações");
		System.out.println("2-Cadastro especifico");
		System.out.println("3-Gerenciamento especifico");
		System.out.println("4-Gerenciar Vendas");
		System.out.println("0-Voltar");
		System.out.println("\nInforem a opção desejada");
		Integer op = null;
		while (op == null) {

			try {
				op = Leitor.leInteger();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}

		switch (op) {
		case 1:
			ConfiguracaoView.editar();
			MenuGerente.menusGerente();
			break;
		case 2:
			MenuGerente.menuCadastro();
			MenuGerente.menusGerente();
			break;
		case 3:
			MenuGerente.menuGerenciamento();
			MenuGerente.menusGerente();
			break;
		case 4:
			MenuGerente.menuVendas();
			MenuGerente.menusGerente();
			break;
		case 0:
			MenuPrincipal.menuGeral();
			break;
		default:
			break;
		}
	}

	
	private static void menuCadastro() {
		
	}
	
	private static void menuGerenciamento() {
		
	}
	
	private static void menuVendas() {
		
	}
}
