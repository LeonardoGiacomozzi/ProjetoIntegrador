package edu.sit.controller.cadastro;

import java.util.ArrayList;
import java.util.List;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.bancodedados.dao.VendaDao;
import edu.sit.controller.notaFiscal.GeraArquivoNotaFiscal;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.erro.venda.EErrosVenda;
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
			vendaNova.setProdutos(pedeProdutos());
		} catch (VendaException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(new VendaDao().insere(vendaNova)
					? "\nVenda efetuada com SUCESSO!\n" + "Gerando Nota Fiscal..." + "\nAguarde..." + "\nNota Fiscal gerada com SUCESSO!\n\n"
					: "Falha na venda");
			try {
				NotaFiscal notaFiscal = NotaFiscal.criaNotaFiscal(new VendaDao().consultaCompleta(new VendaDao().pegaUltimoID()));
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
				System.out.println("\n**** LISTA DE FUNCIONÁRIOS ****\n");
				if (FuncionarioView.visualizar()) {
					System.out.print("\nInforme o código do funcionário: \t");
					funcionarioId = Leitor.leInteger();
				}
			} catch (LeituraException | VisualizacaoException e) {
				System.out.println(e.getMessage());
			}
		}
		return funcionarioId;

	}

	private static Integer pedeCliente() throws VendaException {

		Integer clienteId = null;
		while (clienteId == null) {

			try {
				System.out.println("\n\n**** LISTA DE CLIENTES ****\n");
				System.out.print(!ClienteView.visualizar() ? "\nNão foi possível carregar os clientes" : "");
				System.out.print(String.format("%-11s", "\n[0]") + "CADASTRAR NOVO CLIENTE \n");
				System.out.print("\n\nInforme o código do cliente: \t");
				clienteId = Leitor.leInteger();
				if (clienteId == 0) {
					System.out.println(ClienteController.cadastro() == true ? "" : "");
					clienteId = new ClienteDao().pegaUltimoID();
				}
			} catch (ConexaoException | DaoException | LeituraException | CadastroException | VisualizacaoException e) {
				System.out.println(e.getMessage());
				throw new VendaException(EErrosVenda.BUSCA_LISTA_CLIENTE);
			}
		}
		return clienteId;

	}

	private static ArrayList<Produto> pedeProdutos() throws VendaException {

		Integer opcao = Integer.MAX_VALUE;
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		while (opcao != 0) {
			Produto produtoAux = null;
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
					try {
						produtoAux = new ProdutoDao().consulta(opcao);
						System.out.print("\nProduto [" + produtoAux.getNome() + "]" +
										 "\nDisponível [" + produtoAux.getQuantidade() + "]" +
										 "\nQuantos deseja comprar: \t");
						while (quantidade == null || quantidade > produtoAux.getQuantidade() || quantidade <= 0) {
							try {
								quantidade = Leitor.leInteger();

								if (quantidade > produtoAux.getQuantidade() || quantidade <= 0) {
									System.out.println("\nQuantidade indisponível...");
								} else {
									produtoAux.setQuantidade(produtoAux.getQuantidade() - quantidade);
									new ProdutoDao().altera(produtoAux);
								}
							} catch (LeituraException e) {
								System.out.println(e.getMessage());
							}
						}
						produtos.add(produtoAux);
						System.out.println("\n\nValor Total até o momento: [R$" + 
											precoAtual(produtoAux.getValorUnitario(), quantidade) + "]\n");
						vendaNova.setValor((vendaNova.getValor()==null?0:vendaNova.getValor()) + precoAtual(produtoAux.getValorUnitario(), quantidade));
						System.out.println("Deseja continuar comprando?\n" +
										   "Aperte [1] para Continuar comprando...\n" +
										   "Aperte [0] para Finalizar COMPRA.");
						opcao = Leitor.leInteger();
					} catch (DaoException e) {
						System.out.println(e.getMessage() + "\nErro ao adicionar o produto");
					}
				}

			} catch (DaoException | ConexaoException | LeituraException e) {
				System.out.println(e.getMessage());
				throw new VendaException(EErrosVenda.BUSCA_LISTA_PRODUTO);
			}

		}
		return produtos;
	}

	private static Double precoAtual(Double valorUnitario, Integer quantidade) {

		valor = valor + (valorUnitario != null ? valorUnitario : 0) * (quantidade != null ? quantidade : 0);
		return valor;
	}

	
}
