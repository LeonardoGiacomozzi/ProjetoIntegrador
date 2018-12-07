package edu.sit.controller.cadastro;

import java.util.ArrayList;
import java.util.List;

import edu.sit.DataObject.ProdutoQuantidade;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.bancodedados.dao.VendaDao;
import edu.sit.controller.notaFiscal.GeraArquivoNotaFiscal;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.erro.venda.VendaException;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.NotaFiscal;
import edu.sit.model.Produto;
import edu.sit.model.Venda;
import edu.sit.uteis.Leitor;
import edu.sit.view.controllers.ClienteView;
import edu.sit.view.controllers.FuncionarioView;

public class VendaController {

	private static Venda vendaNova = null;

	public static boolean efetuaVenda() throws CadastroException {

		Integer funcionario = null;
		Integer cliente = null;
		System.out.print("\n**** EFETUAR VENDA ****\n");
		try {
			funcionario = pedeFuncionario();
			cliente = pedeCliente();
			vendaNova = Venda.criaVenda(cliente, funcionario);
			vendaNova.setProdutos(pedeProdutos());
		} catch (VendaException e) {
			System.out.println(e.getMessage());
		}
		try {
			if (vendaNova.getValor()>0) {
				
				System.out.println(new VendaDao().insere(vendaNova)
						? "\nVenda efetuada com SUCESSO!\n" + "Gerando Nota Fiscal..." + "\nAguarde..."
						+ "\nNota Fiscal gerada com SUCESSO!\n\n"
						: "Falha na venda");

			}
			try {
				NotaFiscal notaFiscal = NotaFiscal
						.criaNotaFiscal(new VendaDao().consultaCompleta(new VendaDao().pegaUltimoID()));
				GeraArquivoNotaFiscal.geraArquivo(notaFiscal);
			} catch (NotaFiscalException e) {
				System.out.println(e.getMessage());
			}
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	private static Integer pedeFuncionario() {

		Integer funcionarioId = null;
		while (funcionarioId == null) {

			try {
				if (FuncionarioView.visualizar()) {
					System.out.print("\nInforme o código do funcionário: \t");
					try {
						funcionarioId = Leitor.leInteger();
					} catch (LeituraException e2) {
						System.out.println(e2.getMessage());
					}

					try {
						while (new FuncionarioDao().consulta(funcionarioId) == null) {
							System.out.println("\nCódigo de Funcionário Inválido!");
							try {
								FuncionarioView.visualizar();
								System.out.print("\nInforme o código do funcionário novamente: \t");
							} catch (VisualizacaoException e1) {
								System.out.println(e1.getMessage());
							}
							try {
								funcionarioId = Leitor.leInteger();
							} catch (LeituraException e) {
								System.out.println(e.getMessage());
							}

						}
					} catch (DaoException | ConexaoException  e) {
						System.out.println(e.getMessage());
					}

				}
			} catch (VisualizacaoException e) {
				System.out.println(e.getMessage());
			}

		}
		return funcionarioId;

	}

	private static Integer pedeCliente() throws VendaException {
		Integer clienteId = null;
		while (clienteId == null) {
			try {
				System.out.print(!ClienteView.visualizar() ? "\nNão foi possível carregar os clientes" : "");
			} catch (VisualizacaoException e) {
				System.out.println(e.getMessage());
			}
			System.out.print(String.format("%-11s", "\n[0]") + "CADASTRAR NOVO CLIENTE \n");
			System.out.print("\n\nInforme o código do cliente: \t");

			try {
				clienteId = Leitor.leInteger();
			} catch (LeituraException e2) {
				System.out.println(e2.getMessage());
			}

			try {
				while (new ClienteDao().consulta(clienteId) == null) {
					System.out.println("\nCódigo de Cliente Inválido!");
					try {
						ClienteView.visualizar();
						System.out.print("\nInforme o código do Cliente novamente: \t");
					} catch (VisualizacaoException e1) {
						System.out.println(e1.getMessage());
					}

					try {
						clienteId = Leitor.leInteger();
					} catch (LeituraException e) {
						System.out.println(e.getMessage());
					}
				}
			} catch (DaoException | ConexaoException e1) {
				System.out.println(e1.getMessage());
			}

			if (clienteId == 0) {
				try {
					System.out.println(ClienteController.cadastro() == true ? "" : "");
				} catch (CadastroException e) {
					System.out.println(e.getMessage());
				}
				try {
					clienteId = new ClienteDao().pegaUltimoID();
				} catch (DaoException | ConexaoException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return clienteId;

	}

	private static ArrayList<ProdutoQuantidade> pedeProdutos() {

		Integer opcao = Integer.MAX_VALUE;
		ArrayList<ProdutoQuantidade> produtos = new ArrayList< ProdutoQuantidade>();
		while (opcao != 0) {
			ProdutoQuantidade produtoAux = new ProdutoQuantidade();
			Integer quantidade = 0;
			try {
				List<Produto> produtosBanco = new ProdutoDao().consultaTodosDisponiveisCompleto();
				System.out.println("\n**** LISTA DE PRODUTOS ****\n");
				System.out.println(String.format("%-10s", "Codigo") + String.format("%-19s", "Nome")
						+ String.format("%-13s", "Fornecedor") + String.format("%-13s", "Categoria")
						+ String.format("%-8s", "Valor") + String.format("%-6s", "Quantidade"));
				for (Produto produto : produtosBanco) {
					System.out.println(String.format("%-10s", "[" + produto.getId() + "]")
							+ String.format("%-20s", produto.getNome())
							+ String.format("%-13s", produto.getFornecedor().getNome())
							+ String.format("%-13s", produto.getCategoria().getNome())
							+ String.format("%-10s", produto.getValorUnitario()) + produto.getQuantidade());
				}
				System.out.print("\nInforme o código do produto que deseja comprar: \t");
				opcao = Leitor.leInteger();
				if (opcao != 0) {


						try {
							produtoAux.setItensPedido(new ProdutoDao().consulta(opcao));
							if (produtoAux.getItensPedido() != null && produtoAux.getItensPedido().getQuantidade()>0) {

								System.out.print("\nProduto [" + produtoAux.getItensPedido().getNome() + "]"
										+ "\nDisponível [" + produtoAux.getItensPedido().getQuantidade() + "]"
										+ "\nSair [0]"
										+ "\nQuantos deseja comprar: \t");
								do{
									try {
										quantidade = Leitor.leInteger();

										if (quantidade > produtoAux.getItensPedido().getQuantidade()
												|| quantidade < 0) {
											System.out.print("\nQuantidade Indisponível!\n\nDigite novamente a quantidade desejada: \t");
										} else {
											produtoAux.setQuantidadeProduto(quantidade);
											produtoAux.getItensPedido().setQuantidade(
													produtoAux.getItensPedido().getQuantidade() - quantidade);
											new ProdutoDao().altera(produtoAux.getItensPedido());
											produtos.add(produtoAux);
											vendaNova.setValor((vendaNova.getValor() == null ? 0 : vendaNova.getValor())
													+ precoAtual(produtoAux.getItensPedido().getValorUnitario(), quantidade));
										}
										quantidade = 0;
									} catch (LeituraException e) {
										System.out.println(e.getMessage());
										System.out.print("\nTente novamente: \t");
									}
								}while (quantidade == null || quantidade > produtoAux.getItensPedido().getQuantidade()
										|| quantidade < 0) ;
								System.out.println("\n\nValor Total até o momento: [R$" + vendaNova.getValor() + "]\n");
								System.out.println(
										"Deseja continuar comprando?\n" + "Aperte [1] para Continuar comprando...\n"
												+ "Aperte [0] para Finalizar COMPRA.");
								opcao = Leitor.leInteger();
								
							} else {
								System.out.println("\nCódigo de Produto Inválido!");
							}

						} catch (DaoException e) {
							System.out.println(e.getMessage() + "\nErro ao adicionar o produto");
						}
					}
				

			} catch (DaoException | ConexaoException | LeituraException e) {
				System.out.println(e.getMessage());
			}

		}
		return produtos;
	}

	private static Double precoAtual(Double valorUnitario, Integer quantidade) {
	return (valorUnitario != null ? valorUnitario : 0) * (quantidade != null ? quantidade : 0);
	}

}
