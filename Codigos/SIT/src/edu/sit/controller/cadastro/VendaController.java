package edu.sit.controller.cadastro;

import java.util.ArrayList;

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
import edu.sit.model.Venda;
import edu.sit.uteis.Leitor;
import edu.sit.view.controllers.ClienteView;
import edu.sit.view.controllers.FuncionarioView;
import edu.sit.view.controllers.ProdutoView;

public class VendaController {

	private static Venda vendaNova = null;

	public static boolean efetuaVenda() throws CadastroException {

		Integer funcionario = null;
		Integer cliente = null;
		System.out.print("\n**** EFETUAR VENDA ****\n");
		try {
			funcionario = pedeFuncionario();
			cliente = pedeCliente();
			if (cliente == 0) {
				return false;
			}
			vendaNova = Venda.criaVenda(cliente, funcionario);
			vendaNova.setProdutos(pedeProdutos());
		} catch (VendaException e) {
			System.out.println(e.getMessage());
		}
		try {
			if (vendaNova.getValor() > 0) {

				System.out.println(new VendaDao().insere(vendaNova)
						? "\nVenda efetuada com SUCESSO!\n" + "Gerando Nota Fiscal..." + "\nAguarde..."
								+ "\nNota Fiscal gerada com SUCESSO!\n"
						: "Falha na venda");

			}
			System.out.print("\n");
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
								funcionarioId = Leitor.leInteger();
							} catch (VisualizacaoException | LeituraException e1) {
								System.out.println(e1.getMessage());
							}
						}
					} catch (DaoException | ConexaoException e) {

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
		try {
			while (clienteId == null || new ClienteDao().consulta(clienteId) == null && clienteId != 0) {
				System.out.print(!ClienteView.visualizar() ? "\nNão foi possível carregar os clientes" : "");
				System.out.print(String.format("%-11s", "\n[0]") + "CADASTRAR NOVO CLIENTE \n");
				System.out.print("\nInforme o código do cliente: \t");
				try {
					clienteId = Leitor.leInteger();
					if (new ClienteDao().consulta(clienteId) == null && clienteId != 0) {
						System.out.print("\nCódigo de Cliente Inválido!\n");
					}
				} catch (LeituraException e2) {
					System.out.println(e2.getMessage());
				}
			}
		} catch (DaoException | ConexaoException | VisualizacaoException e1) {
			System.out.println(e1.getMessage());
		}
		if (clienteId != null && clienteId == 0) {
			try {
				if (!ClienteController.cadastro()) {
					return 0;
				}
				
				clienteId = new ClienteDao().pegaUltimoID();
			} catch (CadastroException | DaoException | ConexaoException e) {
				System.out.println(e.getMessage());
			}
		}
		return clienteId == null ? 0 : clienteId;
	}

	private static ArrayList<ProdutoQuantidade> pedeProdutos() {

		Integer opcao = Integer.MAX_VALUE;
		ArrayList<ProdutoQuantidade> produtos = new ArrayList<ProdutoQuantidade>();
		while (opcao != Integer.MIN_VALUE) {
			ProdutoQuantidade produtoAux = new ProdutoQuantidade();
			Integer quantidade = Integer.MAX_VALUE;
			try {
				ProdutoView.visualizar();
				System.out.print("\nInforme o código do produto que deseja comprar: \t");
				opcao = Leitor.leInteger();
				if (opcao != 0) {

					try {
						produtoAux.setItensPedido(new ProdutoDao().consulta(opcao));
						if (produtoAux.getItensPedido() != null && produtoAux.getItensPedido().getQuantidade() > 0) {

							while ((quantidade != null && quantidade != produtoAux.getItensPedido().getQuantidade()
									&& quantidade > produtoAux.getItensPedido().getQuantidade() && quantidade > 0)
									|| quantidade < 0) {
								System.out.print("\nProduto [" + produtoAux.getItensPedido().getNome() + "]"
										+ "\nDisponível [" + produtoAux.getItensPedido().getQuantidade() + "]"
										+ "\nVoltar [0]" + "\nQuantos deseja comprar: \t");
								try {
									quantidade = Leitor.leInteger();

									if (quantidade <= produtoAux.getItensPedido().getQuantidade() && quantidade >= 0) {

										produtoAux.setQuantidadeProduto(quantidade);
										produtos.add(produtoAux);
										vendaNova.setValor(
												(vendaNova.getValor() == null ? 0 : vendaNova.getValor()) + precoAtual(
														produtoAux.getItensPedido().getValorUnitario(), quantidade));
										produtoAux.getItensPedido().setQuantidade(
												produtoAux.getItensPedido().getQuantidade() - quantidade);
										new ProdutoDao().altera(produtoAux.getItensPedido());
										quantidade = 0;
									} else {
										System.out.print("\nQuantidade Indisponível!\n");
									}
								} catch (LeituraException e) {
									System.out.println(e.getMessage());
								}
							}
							System.out.print("\n\nValor Total até o momento: [R$" + vendaNova.getValor() + "]\n");
							System.out.println("Deseja continuar comprando?\n"
									+ "Aperte [1] para Continuar comprando...\n" + "Aperte [0] para Finalizar COMPRA.");
							opcao = Leitor.leInteger();
							if (opcao == 0) {
								opcao = Integer.MIN_VALUE;
							}

						} else if (produtoAux.getItensPedido() != null && produtoAux.getItensPedido().getQuantidade() == 0) {
							System.out.print("\nProduto fora de estoque! Precisa repor!\n");
						} else {
							System.out.print("\nCódigo de Produto Inválido!\n");
						}

					} catch (DaoException e) {
						System.out.println(e.getMessage() + "\nErro ao adicionar o produto");
					}
				}

			} catch (ConexaoException | LeituraException | VisualizacaoException e) {
				System.out.println(e.getMessage());
			}
		}
		return produtos;
	}

	private static Double precoAtual(Double valorUnitario, Integer quantidade) {
		return (valorUnitario != null ? valorUnitario : 0) * (quantidade != null ? quantidade : 0);
	}

}
