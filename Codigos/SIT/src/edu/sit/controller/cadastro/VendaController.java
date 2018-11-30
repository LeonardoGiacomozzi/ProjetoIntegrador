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
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.NotaFiscal;
import edu.sit.model.Produto;
import edu.sit.model.Venda;
import edu.sit.uteis.Leitor;

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
					? "\nVenda efetuada com SUCESSO!\n\n" + "\n\tGerando Nota Fiscal..." + "\n\tAguarde..."
					: "Falha na venda");
			try {
				NotaFiscal notaFiscal = NotaFiscal.criaNotaFiscal(vendaNova);
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
				System.out.println("\n**** LISTA DE FUNCION�RIOS ****\n");
				if (FuncionarioController.visualizar()) {
					System.out.print("\nInforme o c�digo do funcion�rio: \t");
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
				System.out.print(!ClienteController.visualizar() ? "\nN�o foi poss�vel carregar os clientes" : "");
				System.out.print(String.format("%-11s", "\n[0]") + "CADASTRAR NOVO CLIENTE \n");
				System.out.print("\n\nInforme o c�digo do cliente: \t");
				clienteId = Leitor.leInteger();
				System.out.println("\n");
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
				System.out.println("**** LISTA DE PRODUTOS ****\n");
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
				System.out.println(String.format("%-10s", "\n[0]") + "FINALIZAR COMPRA");
				System.out.print("\n\nInforme o c�digo do produto que deseja comprar: \t");
				opcao = Leitor.leInteger();
				if (opcao != 0) {
					try {
						produtoAux = new ProdutoDao().consulta(opcao);
						System.out.println("Informe a quantidade que deseja comprar...\t\tDispon�vel\t:"
								+ produtoAux.getQuantidade());
						while (quantidade == null || quantidade > produtoAux.getQuantidade() || quantidade <= 0) {

							try {
								quantidade = Leitor.leInteger();

								if (quantidade > produtoAux.getQuantidade() || quantidade <= 0) {
									System.out.println("\n\tQuantidade Indispon�vel");
								} else {
									produtoAux.setQuantidade(produtoAux.getQuantidade() - quantidade);
									new ProdutoDao().altera(produtoAux);
								}
							} catch (LeituraException e) {
								System.out.println(e.getMessage());
							}
						}
						produtos.add(produtoAux);
						System.out.println("-------------------------------------------------");
						System.out.println("----------------------------------R$"
								+ precoAtual(produtoAux.getValorUnitario(), quantidade));
						System.out.println("-------------------------------------------------");
						vendaNova.setValor(vendaNova.getValor() + precoAtual(produtoAux.getValorUnitario(), quantidade));
					} catch (DaoException e) {
						System.out.println(e.getMessage() + "\n Erro ao adicionar o produto");
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

	public static boolean visualizar() throws VisualizacaoException {

		try {
			for (Venda venda : new VendaDao().consultaTodos()) {
				System.out.println("#" + venda.getId() + " ----------- " + venda.getValor() + " ----------- "
						+ venda.getClienteId());
				System.out.println("-------------------------Produtos-------------------------");
				for (Produto produto : venda.getProdutos()) {
					System.out.println("\t\t#" + produto.getId() + " ----------- " + produto.getNome());
				}
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_VENDAS);
		}
	}
}
