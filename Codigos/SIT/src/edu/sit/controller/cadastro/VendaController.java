package edu.sit.controller.cadastro;

import java.util.ArrayList;
import java.util.List;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.bancodedados.dao.VendaDao;
import edu.sit.controller.notaFiscal.GeraArquivoNotaFiscal;
import edu.sit.controller.notaFiscal.GeraBinNotaFiscal;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.erro.venda.EErrosVenda;
import edu.sit.erro.venda.VendaException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Cliente;
import edu.sit.model.Funcionario;
import edu.sit.model.NotaFiscal;
import edu.sit.model.Produto;
import edu.sit.model.Venda;
import edu.sit.uteis.Leitor;

public class VendaController {

	public boolean EfetuaVenda() {

		Integer funcionario = null;
		Integer cliente = null;
		ArrayList<Produto> produtos= null;
		Double valor = null;
		
		System.out.print("*****GERAR VENDA*****");

		try {
			funcionario = pedeFuncionario();
			cliente = pedeCliente();
			produtos = pedeProdutos();
		} catch (VendaException e) {
			System.out.println(e.getMessage());
		}
		try {
			Venda vendaNova = Venda.criaVenda(cliente, funcionario, produtos, valor);
			System.out.println(new VendaDao().insere(vendaNova)?"Venda efetuada com sucesso!!"
					+ "\n\tGerando Nota Fiscal"
					+ "\n\tAguarde":"Falha na venda");
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

				List<Funcionario> funcionarios = new FuncionarioDao().consultaTodos();
				for (Funcionario funcionario : funcionarios) {
					System.out.println("\n\t" + funcionario.getId() + " --------- " + funcionario.getNome());
				}
				System.out.println("---------------------------------------------------------");
				System.out.println("Informe o codigo do funcionario\t:");
				funcionarioId = Leitor.leInteger();
			} catch (ConexaoException | DaoException | LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return funcionarioId;

	}

	private static Integer pedeCliente() throws VendaException {

		Integer clienteId = null;
		while (clienteId == null) {

			try {

				List<Cliente> clientes = new ClienteDao().consultaTodos();
				for (Cliente cliente : clientes) {
					System.out.println("\n\t" + cliente.getId() + " --------- " + cliente.getNome());
				}
				System.out.println("\n\t0 --------- CADASTRAR NOVO");
				System.out.println("---------------------------------------------------------");
				System.out.println("Informe o codigo do funcionario\t:");
				clienteId = Leitor.leInteger();
				if (clienteId == 0) {
					System.out.println(ClienteController.cadastro() ? "Cliente cadastrado com sucesso" : "Falha");
					clienteId = new ClienteDao().pegaUltimoID();
				}
			} catch (ConexaoException | DaoException | LeituraException | CadastroExeption e) {
				System.out.println(e.getMessage());
				throw new VendaException(EErrosVenda.BUSCA_LISTA_CLIENTE);
			}
		}
		return clienteId;

	}

	private static ArrayList<Produto> pedeProdutos() throws VendaException {

		Integer opcao = Integer.MAX_VALUE;
		ArrayList<Produto> produtos =  new ArrayList<Produto>();
		while (opcao != 0) {
			Produto produtoAux=null;
			Integer quantidade = 0;
			try {
				List<Produto> produtosBanco = new ProdutoDao().consultaTodosCompleto();
				System.out.println("****************LISTA DE PRODUTOS****************");
				System.out.println("-------------------------------------------------");
				System.out.println("Codigo ----- Nome ----- Fornecedor ----- Categoria ----- Valor ----- Quantidade");
				for (Produto produto : produtosBanco) {
					System.out.println(produto.getId() + " ----- " 
									+ produto.getNome() + " ----- "
									+ produto.getFornecedor().getNome() + " ----- " 
									+ produto.getCategoria().getNome() + " ----- " 
									+ produto.getValorUnitario() + " ----- " 
									+ produto.getQuantidade());
				}
				System.out.println("o ----- FINALIZAR COMPRA");
				System.out.println("-------------------------------------------------");
				opcao = Leitor.leInteger();
				if (opcao!=0) {
					
					try {
						produtoAux =new ProdutoDao().consulta(opcao);
						System.out.println("Informe a quantidade que deseja comprar\t\tDisponivel\t:"+produtoAux.getQuantidade());
						while (quantidade == null || quantidade>produtoAux.getQuantidade() || quantidade<=0) {
							
							try {
								quantidade = Leitor.leInteger();
								
								if (quantidade>produtoAux.getQuantidade() || quantidade<=0) {
									System.out.println("\n\tQuantidade Indisponivel");
								}
							} catch (LeituraException e) {
								System.out.println(e.getMessage());
							}
						}
						produtos.add(produtoAux);
						System.out.println("-------------------------------------------------");
						System.out.println("----------------------------------R$"+precoAtual(produtoAux.getValorUnitario(),quantidade));
						System.out.println("-------------------------------------------------");
					} catch (DaoException e) {
						System.out.println(e.getMessage()+"\n Erro ao adicionar o produto");
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
		// TODO Auto-generated method stub
		return null;
	}

}
