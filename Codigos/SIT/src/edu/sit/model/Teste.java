package edu.sit.model;

import java.util.ArrayList;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.bancodedados.dao.VendaDao;
import edu.sit.controller.notaFiscal.GeraArquivoNotaFiscal;
import edu.sit.controller.relatorio.RelatorioVenda;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.erros.dao.DaoException;
import edu.sit.uteis.cadastro.UtilCadastro;

public class Teste {
	public static void main(String[] args) throws DaoException, ConexaoException, NotaFiscalException {

//		try {
//		System.out.println(Populador.cliente() ? "foi" : "n�oFoi");
//				System.out.println(Populador.funcionario() ? "foi" : "n�oFoi");
//			System.out.println(Populador.categoria() ? "foi" : "n�oFoi");
//			System.out.println(Populador.fornecedor() ? "foi" : "n�oFoi");
//		System.out.println(Populador.produto() ? "foi" : "n�oFoi");
//		System.out.println(Populador.venda() ? "foi" : "n�oFoi");
//		} catch (InstalacaoException e) {
//		System.out.println(e.getMessage());
//		}
		// System.out.println(System.getProperty("user.dir"));
		// Cria Tabela Banco
		//ClienteDao teste = new ClienteDao();
//		try {
		// System.out.println(teste.criaTabela());
//		//Exclui tabela
//			//System.out.println(teste.excluiTabela()); 
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		//Insere 1 por 1
//		System.out.println(teste.insere(Cliente.criaClienteBanco("Maria", LocalDate.parse("25/06/1998", DateTimeFormatter.ofPattern(
//				"dd/MM/yyyy")), "Rua", "111.111.111-11", new ContatoDao().pegaUltimoID())));

//		//Insere Varios por Lista
//		//List<Cliente> lcliente = new ArrayList<>();
//		//lcliente.add(Cliente.criaClienteBanco("Roberto", LocalDate.parse("12/09/1984", DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
//		//		"Avenida", "222.222.222-22", 2));
//		//lcliente.add(Cliente.criaClienteBanco("Larissa", LocalDate.parse("01/12/1991", DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
//		//		"Travessa", "333.333.333-33", 3));
//		//lcliente.add(Cliente.criaClienteBanco("Carol", LocalDate.parse("13/02/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
//		//		"Beco", "444.444.444-44", 4));
//		//System.out.println(teste.insereVarios(lcliente));
//		
//		
		// System.out.println(teste.consulta(1));
		// System.out.println(teste.getFullProperty(1));
//		
//		//Consulta por id
//		//System.out.println(teste.consulta(1));
//		
//		//Consulta todos
		// System.out.println(teste.consultaTodos());
		// System.out.println(teste.consultaCPF("111.111.111-11"));
//		
//		
//		//Exclui apenas 1 linha ou varias....
//		//System.out.println(teste.exclui(7,8,9,10));
//		
//		
//		
		//ContatoDao teste1 = new ContatoDao();
		// teste1.criaTabela();
//		//System.out.println(teste1.insere(Contato.criaContato("3336-2333", "hericles_peixer@hotmail.com")));
		// teste1.insere(Contato.criaContato("1111-1111", "22222@hotmail.com"));
//		//List<Contato> teste2 = new ArrayList<>();
//		//teste2.add(Contato.criaContato("4444-3232", "kkkkkk@hotmail.com"));
//		//teste2.add(Contato.criaContato("5555-5555", "sssssss@hotmail.com"));
//		//teste2.add(Contato.criaContato("6666-6666", "qqqqqq@hotmail.com"));
//		//System.out.println(teste1.insereVarios(teste2));
//		//System.out.println(teste1.consulta(1));
//		//System.out.println(teste1.consulta(3));
		// System.out.println(teste1.consultaTodos());
//		//System.out.println(teste1.consultaFaixa(1,2,4));
//		//Contato contato = new ContatoDao().consulta(1);
//		//contato.setTelefone("xxxx-xxxx");
//		//teste1.altera(contato);
//		//System.out.println(teste1.exclui(2,4));
//		//System.out.println(teste1.pegaUltimoID());
		// System.out.println(teste1.pegaUltimoID());
//		
		//FuncionarioDao teste12 = new FuncionarioDao();
		// System.out.println(teste12.criaTabela());
		// System.out.println(teste12.excluiTabela());
//		
//		NotaFiscalDao teste3 = new NotaFiscalDao();
//		//System.out.println(teste3.criaTabela());
//		
		//FornecedorDao teste4 = new FornecedorDao();
		// System.out.println(teste4.criaTabela());
		// System.out.println(teste4.excluiTabela());
		// System.out.println(teste4.insere(Fornecedor.criaFornecedorFull("BGM FUMOs",
		// "xx.xxx.xxx/xxxx-xx", "Jose", new ContatoDao().pegaUltimoID())));
		// System.out.println(teste4.consultaTodos());
		// System.out.println(teste4.exclui(1));
		// System.out.println(teste4.consultaCompleta(1));
		// System.out.println(teste4.consultaCNPJ("xx.xxx.xxx/xxxx-xx"));
		// System.out.println(teste4.consultaTodos());
//		
		//ProdutoDao teste5 = new ProdutoDao();
		// System.out.println(teste5.criaTabela());
		// System.out.println(teste5.consulta(1));
//		System.out.println(teste5.consultaCompleta(1));
////		
//		VendaDao teste6 = new VendaDao();
//		RelatorioVenda.geraArquivo(teste6.pegaVendaDia());
		//GeraArquivoNotaFiscal.geraArquivo(NotaFiscal.criaNotaFiscal(teste6.consultaCompleta(1)));
		// System.out.println(teste6.criaTabela());
		// System.out.println(teste6.consultaCompleta(1));
//		ArrayList<Venda> vendas = teste6.pegaVendaMes();
//		if (vendas == null) {
//			System.out.println("erro");
//		} else {
//		for (Venda venda : vendas) {
//		System.out.println(venda.toString()+"\n"+venda.getValor()+"a");
//		}
//		}
		//CategoriaDao teste7 = new CategoriaDao();
		// System.out.println(teste7.criaTabela());
		// System.out.println(teste7.consulta(1));
		
		//UsuarioDao novo = new UsuarioDao();
		//System.out.println(novo.criaTabela());
		
		//Integer a= new FuncionarioDao().consultaPorCpf(UtilCadastro.pedeCpf());
		//System.out.println(a);
		
	}
}