package edu.sit.controller.cadastro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private static Double valor = 0.0;
	private static Venda vendaNova = null;

	public static boolean efetuaVenda() throws CadastroException {

		Integer funcionario = null;
		Integer cliente = null;
		valor = 0.0;
		System.out.print("\n**** EFETUAR VENDA ****\n");
		try {
			funcionario = pedeFuncionario();
			cliente = pedeCliente();
			vendaNova = Venda.criaVenda(cliente, funcionario);
			ArrayList<ProdutoQuantidade> aux = new ArrayList<ProdutoQuantidade>();
			Map<Integer,ProdutoQuantidade> auxMap = pedeProdutos();
			for (Map.Entry<Integer,ProdutoQuantidade> object : auxMap.entrySet() ) {
				aux.add(object.getValue());
				
			}
			vendaNova.setProdutos(aux);
		} catch (VendaException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(new VendaDao().insere(vendaNova)
					? "\nVenda efetuada com SUCESSO!\n" + "Gerando Nota Fiscal..." + "\nAguarde..."
							+ "\nNota Fiscal gerada com SUCESSO!\n\n"
					: "Falha na venda");
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
							System.out.println("Funcionario Inválido");
							System.out.println("Informe um novo funcionário");
							System.out.print("\nInforme o código do funcionário: \t");
							try {
								FuncionarioView.visualizar();
							} catch (VisualizacaoException e1) {
								System.out.println(e1.getMessage());
							}
							try {
								funcionarioId = Leitor.leInteger();
							} catch (LeituraException e) {
								System.out.println(e.getMessage());
							}

						}
					} catch (DaoException | ConexaoException e) {
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
					System.out.println("Cliente Inválido");
					System.out.println("Informe um novo cliente");
					System.out.println("\n\n**** LISTA DE CLIENTES ****\n");
					try {
						ClienteView.visualizar();
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

	private static HashMap<Integer, ProdutoQuantidade> pedeProdutos() {

		Integer opcao = Integer.MAX_VALUE;
		HashMap<Integer, ProdutoQuantidade> produtos = new HashMap<Integer, ProdutoQuantidade>();
		while (opcao != 0) {
			ProdutoQuantidade produtoAux = new ProdutoQuantidade();
			Integer quantidade = 0;
			try {
				List<Produto> produtosBanco = new ProdutoDao().consultaTodosCompleto();
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

					if (produtos.containsKey(opcao)) {

						produtoAux = produtos.get(opcao);
						System.out.println("Produto [" + produtoAux.getItensPedido().getNome()
								+ "] quantidade no pedido [" + produtoAux.getQuantidadeProduto() + "]"
								+ " quantidade disponivel [" + produtoAux.getItensPedido().getQuantidade() + "]");
						while (quantidade == null || quantidade > produtoAux.getItensPedido().getQuantidade()
								|| quantidade <= 0) {
							try {
								quantidade = Leitor.leInteger();

								if (quantidade > produtoAux.getItensPedido().getQuantidade() || quantidade <= 0) {
									System.out.println("\nQuantidade indisponível...");
								} else {
									Integer aux;
									if (quantidade>produtoAux.getQuantidadeProduto()) {
										aux = quantidade-produtoAux.getQuantidadeProduto();
										produtoAux.getItensPedido()
										.setQuantidade(produtoAux.getItensPedido().getQuantidade() -aux);
										
										vendaNova.setValor((vendaNova.getValor() == null ? 0 : vendaNova.getValor())
												+ precoAtual(produtoAux.getItensPedido().getValorUnitario(), quantidade,1));
									}else {
										aux = produtoAux.getQuantidadeProduto()-quantidade;
										produtoAux.getItensPedido()
										.setQuantidade(produtoAux.getItensPedido().getQuantidade() + aux);
										vendaNova.setValor((vendaNova.getValor() == null ? 0 : vendaNova.getValor())
												+ precoAtual(produtoAux.getItensPedido().getValorUnitario(), aux,0));
									}
									produtoAux.setQuantidadeProduto(quantidade);
									new ProdutoDao().altera(produtoAux.getItensPedido());
									produtos.put(opcao, produtoAux);
								}
							} catch (LeituraException e) {
								System.out.println(e.getMessage());
							}
						}

					} else {

						try {
							produtoAux.setItensPedido(new ProdutoDao().consulta(opcao));
							if (produtoAux.getItensPedido() != null) {

								System.out.print("\nProduto [" + produtoAux.getItensPedido().getNome() + "]"
										+ "\nDisponível [" + produtoAux.getItensPedido().getQuantidade() + "]"
										+ "\nQuantos deseja comprar: \t");
								while (quantidade == null || quantidade > produtoAux.getItensPedido().getQuantidade()
										|| quantidade <= 0) {
									try {
										quantidade = Leitor.leInteger();

										if (quantidade > produtoAux.getItensPedido().getQuantidade()
												|| quantidade <= 0) {
											System.out.println("\nQuantidade indisponível...");
										} else {
											produtoAux.setQuantidadeProduto(quantidade);
											produtoAux.getItensPedido().setQuantidade(
													produtoAux.getItensPedido().getQuantidade() - quantidade);
											new ProdutoDao().altera(produtoAux.getItensPedido());
											produtos.put(opcao, produtoAux);
											vendaNova.setValor((vendaNova.getValor() == null ? 0 : vendaNova.getValor())
													+ precoAtual(produtoAux.getItensPedido().getValorUnitario(), quantidade,1));
										}
									} catch (LeituraException e) {
										System.out.println(e.getMessage());
									}
								}
								System.out.println("\n\nValor Total até o momento: [R$" + vendaNova.getValor() + "]\n");
								System.out.println(
										"Deseja continuar comprando?\n" + "Aperte [1] para Continuar comprando...\n"
												+ "Aperte [0] para Finalizar COMPRA.");
								opcao = Leitor.leInteger();
							} else {
								System.out.println("Valor Invalido");
							}

						} catch (DaoException e) {
							System.out.println(e.getMessage() + "\nErro ao adicionar o produto");
						}
					}
				}

			} catch (DaoException | ConexaoException | LeituraException e) {
				System.out.println(e.getMessage());
			}

		}
		return produtos;
	}

	private static Double precoAtual(Double valorUnitario, Integer quantidade,Integer operacao) {
		if (operacao==1) {
			
			valor = valor + (valorUnitario != null ? valorUnitario : 0) * (quantidade != null ? quantidade : 0);
		}else {
			valor = valor - (valorUnitario != null ? valorUnitario : 0) * (quantidade != null ? quantidade : 0);

		}
		return valor;
	}

}
