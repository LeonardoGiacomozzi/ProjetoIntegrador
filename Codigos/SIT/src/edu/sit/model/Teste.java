package edu.sit.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.bancodedados.dao.NotaFiscalDao;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.bancodedados.dao.VendaDao;
import edu.sit.erros.dao.DaoException;

public class Teste {	 
	public static void main(String[] args) throws DaoException, ConexaoException {
		
//		//Cria Tabela Banco
		ClienteDao teste = new ClienteDao();
//		try {
//			//System.out.println(teste.criaTabela());
//		//Exclui tabela
//			//System.out.println(teste.excluiTabela()); 
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		//Insere 1 por 1
////		System.out.println(teste.insere(Cliente.criaClienteBanco("Maria", LocalDate.parse("25/06/1998", DateTimeFormatter.ofPattern(
////			"dd/MM/yyyy")), "Rua", "111.111.111-11", new ContatoDao().pegaUltimoID())));
//		
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
       //System.out.println(teste.getFullProperty(1));
//		
//		//Consulta por id
//		//System.out.println(teste.consulta(1));
//		
//		//Consulta todos
//		//System.out.println(teste.consultaTodos());
//		
//		
//		
//		//Exclui apenas 1 linha ou varias....
//		//System.out.println(teste.exclui(7,8,9,10));
//		
//		
//		
		ContatoDao teste1 = new ContatoDao();
//		//teste1.criaTabela();
//		//System.out.println(teste1.insere(Contato.criaContato("3336-2333", "hericles_peixer@hotmail.com")));
		//teste1.insere(Contato.criaContato("1111-1111", "22222@hotmail.com"));
//		//List<Contato> teste2 = new ArrayList<>();
//		//teste2.add(Contato.criaContato("4444-3232", "kkkkkk@hotmail.com"));
//		//teste2.add(Contato.criaContato("5555-5555", "sssssss@hotmail.com"));
//		//teste2.add(Contato.criaContato("6666-6666", "qqqqqq@hotmail.com"));
//		//System.out.println(teste1.insereVarios(teste2));
//		//System.out.println(teste1.consulta(1));
//		//System.out.println(teste1.consulta(3));
//		//System.out.println(teste1.consultaTodos());
//		//System.out.println(teste1.consultaFaixa(1,2,4));
//		//Contato contato = new ContatoDao().consulta(1);
//		//contato.setTelefone("xxxx-xxxx");
//		//teste1.altera(contato);
//		//System.out.println(teste1.exclui(2,4));
//		//System.out.println(teste1.pegaUltimoID());
		//System.out.println(teste1.pegaUltimoID());
//		
		FuncionarioDao teste12 = new FuncionarioDao();
//		//System.out.println(teste2.criaTabela());
//		
//		NotaFiscalDao teste3 = new NotaFiscalDao();
//		//System.out.println(teste3.criaTabela());
//		
		FornecedorDao teste4 = new FornecedorDao();
//		//System.out.println(teste4.criaTabela());
//		//System.out.println(teste4.excluiTabela());
		//System.out.println(teste4.insere(Fornecedor.criaFornecedorFull("BGM FUMOs", "xx.xxx.xxx/xxxx-xx", "Jose", new ContatoDao().pegaUltimoID())));
		//System.out.println(teste4.consultaTodos());
		//System.out.println(teste4.exclui(1));
		System.out.println(teste4.getFullProperty(2));
//		
//		ProdutoDao teste5 = new ProdutoDao();
//		//System.out.println(teste5.criaTabela());
//		
//		VendaDao teste6 = new VendaDao();
//		//System.out.println(teste6.criaTabela());
	}
}