package edu.sit.controller.cadastro;

import java.util.ArrayList;
import java.util.List;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.bancodedados.dao.VendaDao;
import edu.sit.controller.notaFiscal.GeraArquivoNotaFiscal;
import edu.sit.controller.notaFiscal.GeraBinNotaFiscal;
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

	public static boolean efetuaVenda() throws CadastroException {

		Integer funcionario = null;
		Integer cliente = null;
		ArrayList<Produto> produtos = null;
		valor = 0.0;
		System.out.print("\n*** EFETUAR VENDA ***\n");

		try {
			funcionario = pedeFuncionario();
			cliente = pedeCliente();
			produtos = pedeProdutos();
		} catch (VendaException e) {
			System.out.println(e.getMessage());
		}
		try {
			Venda vendaNova = Venda.criaVenda(cliente, funcionario, produtos, valor);
			System.out.println(new VendaDao().insere(vendaNova)
					? "\nVenda efetuada com SUCESSO!" + "\n\tGerando Nota Fiscal..." + "\n\tAguarde..."
					: "Falha na venda");
			try {
				NotaFiscal notaFiscal = NotaFiscal.criaNotaFiscal(vendaNova);
				GeraArquivoNotaFiscal.geraArquivo(notaFiscal);
				GeraBinNotaFiscal.geraBin(notaFiscal);
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
				System.out.println("\n************LISTA DE FUNCIONÁRIOS***************");
				if (FuncionarioController.visualizar()) {
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
				System.out.println("\n\n****************LISTA DE CLIENTES***************");
				System.out.print(!ClienteController.visualizar()?"\nNão foi possível carregar os clientes":"");
				System.out.print("\n#0 ----------- CADASTRAR NOVO CLIENTE \n");
				System.out.print("\nInforme o código do cliente: \t");
				clienteId = Leitor.leInteger();
				if (clienteId == 0) {
					System.out.println(ClienteController.cadastro() ? "Cliente cadastrado com sucesso" : "Falha");
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
				System.out.println("\n\n***************************LISTA DE PRODUTOS******************************");
				System.out.println(String.format("%-10s", "Codigo") + String.format("%-19s", "Nome") + String.format("%-13s", "Fornecedor") + 
						String.format("%-13s", "Categoria") + String.format("%-8s", "Valor") + String.format("%-6s", "Quantidade"));
				for (Produto produto : produtosBanco) {
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println(String.format("%-10s", "#" + produto.getId()) + String.format("%-20s", produto.getNome()) +
							String.format("%-13s", produto.getFornecedor().getNome()) + String.format("%-13s", produto.getCategoria().getNome()) +
							String.format("%-11s", produto.getValorUnitario()) + String.format("%-6s", produto.getQuantidade()));
				}
				System.out.println("o ----- FINALIZAR COMPRA");
				opcao = Leitor.leInteger();
				if (opcao != 0) {

					try {
						produtoAux = new ProdutoDao().consulta(opcao);
						System.out.println("Informe a quantidade que deseja comprar\t\tDisponivel\t:"
								+ produtoAux.getQuantidade());
						while (quantidade == null || quantidade > produtoAux.getQuantidade() || quantidade <= 0) {

							try {
								quantidade = Leitor.leInteger();

								if (quantidade > produtoAux.getQuantidade() || quantidade <= 0) {
									System.out.println("\n\tQuantidade Indisponível");
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
