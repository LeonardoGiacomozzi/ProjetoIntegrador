package edu.sit.view.menu;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.CategoriaDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.bancodedados.dao.UsuarioDao;
import edu.sit.bancodedados.dao.VendaDao;
import edu.sit.controller.cadastro.CategoriaController;
import edu.sit.controller.cadastro.FornecedorController;
import edu.sit.controller.cadastro.FuncionarioController;
import edu.sit.controller.cadastro.ProdutoController;
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
import edu.sit.view.controllers.ProdutoView;
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
				MenuGerente.menusGerente();
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
			System.out.print("\nERRO - Escolha uma opção válida!\n");
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
				MenuGerente.menuCadastro();
			}
		}

		switch (op) {
		case 1:
			try {
				System.out.print(FuncionarioController.cadastro() ? "" : "");
			} catch (CadastroException e) {
				System.out.println(e.getMessage());
			}
			MenuGerente.menuCadastro();
			break;

		case 2:
			try {
				System.out.print(UsuarioController.cadastro() ? "" : "");
			} catch (CadastroException e) {
				System.out.println(e.getMessage());
			}
			MenuGerente.menuCadastro();
			break;
		case 0:
			MenuGerente.menusGerente();
			break;

		default:
			System.out.print("\nERRO - Escolha uma opção válida!\n");
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
				MenuGerente.menuGerenciamento();
			}
		}

		switch (op) {
		case 1:
			MenuGerente.menuFuncionario();
			break;
		case 2:
			Integer codigoUu = null;
			while (codigoUu == null) {
				try {
					UsuarioView.visualizar();
				} catch (VisualizacaoException e3) {
					System.out.println(e3.getMessage());
				}
				System.out.print("\nSelecione qual usuário deseja trocar a senha: \t");

				try {
					codigoUu = Leitor.leInteger();
				} catch (LeituraException e2) {
					System.out.println(e2.getMessage());
				}

				try {
					while (new UsuarioDao().consulta(codigoUu) == null) {
						System.out.println("Usuario Invalido");
						System.out.println("Informe um novo Usuario");
						try {
							codigoUu = Leitor.leInteger();
						} catch (LeituraException e) {
							System.out.println(e.getMessage());
						}

					}
				} catch (DaoException | ConexaoException e) {
					System.out.println(e.getMessage());
				}

			}
			try {
				UsuarioController.trocaSenha(new UsuarioDao().consulta(codigoUu));
			} catch (DaoException | ConexaoException e1) {
				System.out.println(e1.getMessage());
			}
			MenuGerente.menuGerenciamento();
			break;
		case 3:
			Integer codigo = 0;
			try {
				ProdutoView.visualizar();
			} catch (VisualizacaoException e2) {
				System.out.println(e2.getMessage());
			}
			System.out.print("\nSelecione qual produto deseja alterar: \t");
			try {
				codigo = Leitor.leInteger();
			} catch (LeituraException e2) {
				System.out.println(e2.getMessage());
			}
			while (codigo == 0) {
				try {
					while (new ProdutoDao().consulta(codigo) == null) {
						System.out.println("Valor invalido");
						System.out.println("Informe um valor valido");
						try {
							ProdutoView.visualizar();
						} catch (VisualizacaoException e1) {
							System.out.println(e1.getMessage());
						}
						try {
							codigo = Leitor.leInteger();
						} catch (LeituraException e) {
							System.out.println(e.getMessage());
						}
					}

				} catch (DaoException | ConexaoException e1) {
					System.out.println(e1.getMessage());
				}
			}
			try {
				ProdutoController.editar(codigo);
			} catch (EdicaoException e1) {
				System.out.println(e1.getMessage());
			}
			MenuGerente.menuGerenciamento();

			break;
		case 4:

			Integer codigo1 = 0;
			try {
				CategoriaView.visualizar();
			} catch (VisualizacaoException e5) {
				System.out.println(e5.getMessage());
			}
			System.out.print("\nSelecione qual categoria deseja alterar: \t");
			try {
				codigo1 = Leitor.leInteger();
			} catch (LeituraException e4) {
				System.out.println(e4.getMessage());
			}
			while (codigo1 == 0) {
				try {
					while (new CategoriaDao().consulta(codigo1) == null) {
						try {
							CategoriaView.visualizar();
						} catch (VisualizacaoException e1) {
							System.out.println(e1.getMessage());
						}
						try {
							codigo1 = Leitor.leInteger();
						} catch (LeituraException e) {
							System.out.println(e.getMessage());
						}
					}
				} catch (DaoException | ConexaoException e) {
					System.out.println(e.getMessage());
				}
			}
			try {
				CategoriaController.editar(codigo1);
			} catch (EdicaoException e4) {
				System.out.println(e4.getMessage());
			}
			MenuGerente.menuGerenciamento();

			break;
		case 5:
			Integer codigo2 = 0;
			try {
				FornecedorView.visualizar();
			} catch (VisualizacaoException e3) {
				System.out.println(e3.getMessage());
			}
			System.out.print("\nSelecione qual fornecedor deseja alterar: \t");
			try {
				codigo2 = Leitor.leInteger();
			} catch (LeituraException e2) {
				System.out.println(e2.getMessage());
			}
			while (codigo2 == 0) {
				try {
					while (new FornecedorDao().consulta(codigo2) == null) {
						System.out.println("Informe um valor Válido!");
						try {
							FornecedorView.visualizar();
						} catch (VisualizacaoException e1) {
							System.out.println(e1.getMessage());
						}
						try {
							codigo2 = Leitor.leInteger();
						} catch (LeituraException e) {
							System.out.println(e.getMessage());
						}

					}
				} catch (DaoException | ConexaoException e) {
					System.out.println(e.getMessage());
				}
			}
			try {
				FornecedorController.editar(codigo2);
			} catch (EdicaoException e) {
				System.out.println(e.getMessage());
			}
			MenuGerente.menuGerenciamento();

			break;
		case 0:
			MenuGerente.menusGerente();
			break;
		default:
			System.out.print("\nERRO - Escolha uma opção válida!\n");
			menuGerenciamento();
			break;
		}

	}

	public static void menuVendas() {
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
				MenuGerente.menuVendas();
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
			System.out.print("\nERRO - Escolha uma opção válida!\n");
			MenuGerente.menuVendas();
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
				MenuGerente.menuFuncionario();
			}
		}

		switch (op) {
		case 1:
			try {
				System.out.print("\n");
				Integer a = new FuncionarioDao().consultaPorCpf(UtilCadastro.pedeCpf());
				while (a == 0) {
					System.out.println("\nCPF Inválido!\n");
					a = new FuncionarioDao().consultaPorCpf(UtilCadastro.pedeCpf());
				}
				FuncionarioController.editar(a);
			} catch (EdicaoException | ConexaoException | DaoException e) {
				System.out.println(e.getMessage());
			}
			MenuGerente.menusGerente();
			break;
		case 2:
			Integer editar = 0;
			System.out.print("\nInforme o codigo do funcionário que deseja editar: \t");
			try {
				FuncionarioView.visualizar();
			} catch (VisualizacaoException e1) {
				System.out.println(e1.getMessage());
			}
			while (editar == 0) {
				try {
					editar = Leitor.leInteger();
				} catch (LeituraException e) {
					System.out.println(e.getMessage());
				}
				try {
					while (new FuncionarioDao().consulta(editar) == null) {
						System.out.print("\nValor Inválido!");

						try {
							FuncionarioView.visualizar();
							editar = Leitor.leInteger();
						} catch (LeituraException | VisualizacaoException e) {
							System.out.println(e.getMessage());
						}

					}
				} catch (DaoException | ConexaoException e) {
					System.out.println(e.getMessage());
				}
			}
			try {
				FuncionarioController.editar(editar);
			} catch (EdicaoException e) {
				System.out.println(e.getMessage());
			}

			MenuGerente.menuGerenciamento();
			break;
		case 0:
			MenuGerente.menuGerenciamento();
			break;
		default:
			System.out.print("\nERRO - Escolha uma opção válida!\n");
			MenuGerente.menuFuncionario();
			break;

		}
	}

}
