package edu.sit.view.menu;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.bancodedados.dao.UsuarioDao;
import edu.sit.bancodedados.dao.VendaDao;
import edu.sit.controller.cadastro.CategoriaController;
import edu.sit.controller.cadastro.FornecedorController;
import edu.sit.controller.cadastro.FuncionarioController;
import edu.sit.controller.cadastro.UsuarioController;
import edu.sit.controller.relatorio.RelatorioVenda;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;
import edu.sit.view.configuracao.ConfiguracaoView;
import edu.sit.view.controllers.CategoriaView;
import edu.sit.view.controllers.FornecedorView;
import edu.sit.view.controllers.FuncionarioView;
import edu.sit.view.controllers.UsuarioView;

public class MenuGerente {

	public static void menusGerente() {
		System.out.println("\n**** GERENCIAMENTO ****");
		System.out.println("1 - Configurações");
		System.out.println("2 - Cadastros");
		System.out.println("3 - Edições");
		System.out.println("4 - Gerenciar Vendas");
		System.out.println("0 - Voltar");
		System.out.print("\nInforme a opção desejada: \t");
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
			break;
		case 3:
			MenuGerente.menuGerenciamento();
			break;
		case 4:
			MenuGerente.menuVendas();
			break;
		case 0:
			System.out.print("\n");
			MenuPrincipal.menuGeral();
			break;
		default:
			System.out.println("\nERRO - Escolha uma opção válida!\n");
			MenuGerente.menusGerente();
			break;
		}
	}

	private static void menuCadastro() {
		System.out.println("\n1 - Cadastrar Funcionário");
		System.out.println("2 - Cadastrar Usuário");
		System.out.println("0 - Voltar");
		System.out.print("\nInforme a opção desejada: \t");
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
			try {
				System.out.println(FuncionarioController.cadastro() ? "Cadastro efetuado com sucesso!!" : "");
			} catch (CadastroException e) {
				System.out.println(e.getMessage());
			}
			MenuGerente.menuCadastro();
			break;

		case 2:
			try {
				System.out.println(UsuarioController.cadastro() ? "Cadastro efetuado com sucesso!!" : "");
			} catch (CadastroException e) {
				System.out.println(e.getMessage());
			}
			MenuGerente.menuCadastro();
			break;
		case 0:
			MenuGerente.menusGerente();
			break;

		default:
			System.out.println("\nERRO - Escolha uma opção válida!\n");
			MenuGerente.menuCadastro();
			break;
		}
	}

	private static void menuGerenciamento() {

		System.out.println("\n1 - Editar Funcionário");
		System.out.println("2 - Editar Usuário");
		System.out.println("3 - Editar Produto");
		System.out.println("4 - Editar Categoria");
		System.out.println("5 - Editar Fornecedor");
		System.out.println("0 - Voltar");
		System.out.print("\nInforme a opção desejada: \t");
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
			MenuGerente.menuFuncionario();
			break;
		case 2:
			try {
				UsuarioView.visualizar();
				System.out.print("\nSelecione qual usuário deseja trocar a senha: \t");
				UsuarioController.trocaSenha(new UsuarioDao().consulta(Leitor.leInteger()));
				MenuGerente.menuGerenciamento();
			} catch (VisualizacaoException | DaoException | ConexaoException | LeituraException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			break;
		case 4:
			try {
				CategoriaView.visualizar();
				System.out.print("\nSelecione qual categoria deseja alterar: \t");
				try {
					Integer codigo = Leitor.leInteger();
					CategoriaController.editar(codigo);
					MenuGerente.menuGerenciamento();
				} catch (LeituraException | EdicaoException e) {
					System.out.println(e.getMessage());
				}
			} catch (VisualizacaoException a) {
				System.out.println(a.getMessage());
			}
			break;
		case 5:
			try {
				FornecedorView.visualizar();
				System.out.print("\nSelecione qual fornecedor deseja alterar: \t");
				try {
					Integer codigo = Leitor.leInteger();
					FornecedorController.editar(codigo);
					MenuGerente.menuGerenciamento();
				} catch (EdicaoException | LeituraException a) {
					System.out.println(a.getMessage());
				}
			} catch (VisualizacaoException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 0:
			MenuGerente.menusGerente();
			break;
		default:
			System.out.println("\nERRO - Escolha uma opção válida!\n");
			menuGerenciamento();
			break;
		}

	}

	private static void menuVendas() {
		System.out.println("\n1 - Gerar relatório (Dia)");
		System.out.println("2 - Gerar relatório (Semana)");
		System.out.println("3 - Gerar relatório (Mês)");
		System.out.println("0 - Voltar");
		System.out.print("\nInforme a opção desejada: \t");
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
			try {
				RelatorioVenda.geraArquivo(new VendaDao().pegaVendaDia());
				MenuGerente.menusGerente();
			} catch (DaoException | ConexaoException | NotaFiscalException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			try {
				RelatorioVenda.geraArquivo(new VendaDao().pegaVendaSemana());
				MenuGerente.menusGerente();
			} catch (DaoException | ConexaoException | NotaFiscalException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			try {
				RelatorioVenda.geraArquivo(new VendaDao().pegaVendaMes());
				MenuGerente.menusGerente();
			} catch (DaoException | ConexaoException | NotaFiscalException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 0:	
			MenuGerente.menusGerente();
			break;
		default:
			System.out.println("\nERRO - Escolha uma opção válida!\n");
			MenuGerente.menuFuncionario();
			break;
		}
		
	}

	private static void menuFuncionario() {
		System.out.println("\n1 - Buscar por CPF");
		System.out.println("2 - Listar todos");
		System.out.println("0 - Voltar");
		System.out.print("\nInforme a opção desejada: \t");
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
			try {
				System.out.print("\n");
				Integer a = new FuncionarioDao().consultaPorCpf(UtilCadastro.pedeCpf());
				while(a == 0) {
					System.out.println("\nCPF Inválido!\n");
					System.out.print("\nInforme um CPF válido: \t");
					a= new FuncionarioDao().consultaPorCpf(UtilCadastro.pedeCpf());
				}
				FuncionarioController.editar(a); 
			} catch (EdicaoException | ConexaoException | DaoException e) {
				System.out.println(e.getMessage());
			}
			MenuGerente.menusGerente();
			break;
		case 2:
			try {
				FuncionarioView.visualizar();
				System.out.print("\nInforme o codigo do funcionário que deseja editar: \t");
				FuncionarioController.editar(Leitor.leInteger());
			} catch (VisualizacaoException | EdicaoException | LeituraException e) {
				System.out.println(e.getMessage());
			}
			
			MenuGerente.menusGerente();
			break;
		case 0:
			MenuGerente.menusGerente();
			break;
		default:
			System.out.println("\nERRO - Escolha uma opção válida!\n");
			MenuGerente.menuFuncionario();
			break;

		}
	}

}
