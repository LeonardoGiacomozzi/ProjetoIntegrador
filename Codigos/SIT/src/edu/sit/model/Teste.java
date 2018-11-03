package edu.sit.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.DaoException;

public class Teste {
	 
	 
	    public static void main(String[] args) throws DaoException, ConexaoException {
//	      NotaFiscal nota = NotaFiscal.criaNotaFiscal(Funcionario.criaFuncionario("teste",ECargo.VENDEDOR), Cliente.criaPessoa("TesteCliente", new Date("10/10/1998")), new ArrayList<Produto>(Produto.criaProdutoSemFornecedor("xpto","xprr"
//	    		  ,(Integer)10, (Double) 100)));
//	      GeraAquivoNotaFiscal.geraArquivo(nota, "c:/lixo/teste.txt");
	    	
//	    	        Date dt = new Date ();
//	    	        
//	    	        
//	    	        System.out.println (dt);
//	    	        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss.SSS");
//	    	        df.setTimeZone (TimeZone.getTimeZone ("GMT"));
//	    	        System.out.println (df.format (dt));
//	    	        
//	  ClienteDao teste = new ClienteDao();
	  //System.out.println(teste.criaTabela());
	 // System.out.println(teste.insere(Cliente.criaPessoaBanco("Joao", new Date(), "Rua", "111.111.111-11", 1)));
	 // System.out.println(teste.insere(Cliente.criaPessoaBanco("Maria", new Date(), "Avenida", "222.222.222-22", 2)));
	 // System.out.println(teste.insere(Cliente.criaPessoaBanco("Roberto", new Date(), "Praça", "333.333.333-33", 3)));
	 // System.out.println(teste.insere(Cliente.criaPessoaBanco("Larissa", new Date(), "Travessa", "444.444.444-44", 4)));
	  
	 //System.out.println(teste.insere(Cliente.criaPessoaBanco("Manoela", new java.sql.Date(10051998), "Travessa", "555.555.555-55", 6)));
	  //System.out.println(teste.consulta(5));
	 
	 //List<Cliente> teste1 = new ArrayList<>();
	 //teste1.add(Cliente.criaPessoaBanco("Joao", LocalDate.parse("26/01/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "Rua", "111.111.111-11", 1));
	 //teste1.add(Cliente.criaPessoaBanco("Maria", LocalDate.parse("26/01/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "Avenida", "222.222.222-22", 2));
	// teste1.add(Cliente.criaPessoaBanco("Roberto", LocalDate.parse("26/01/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "Praça", "333.333.333-33", 3));
	 //teste1.add(Cliente.criaPessoaBanco("Larissa", LocalDate.parse("26/01/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "Travessa", "444.444.444-44", 4));
	 //System.out.println(new ClienteDao().insereVarios(teste1));
	   
	   
	    	
	  // try {
		//	ClienteDao test1 = new ClienteDao();
			//Cliente teste3 = new ClienteDao().consulta(2);
		//	teste3.setNome("Xico");
		//	System.out.println(test1.altera(teste3));
	//	} catch (Exception e) {
		//	System.out.println(e.getMessage());
	//	}
	
	  ClienteDao teste = new ClienteDao();
	  System.out.println(teste.consultaFaixa(1,2,3,4));
	
	  List<Cliente> teste1 = new ArrayList<>();
	  teste1.add(Cliente.criaClienteBanco("Bruno", LocalDate.parse("19/08/1924", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "Rua", "111.111.111-11", 1));
	  teste1.add(Cliente.criaClienteBanco("Diego", LocalDate.parse("01/12/1962", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "Avenida", "222.222.222-22", 2));
	  teste1.add(Cliente.criaClienteBanco("Rodrigo", LocalDate.parse("12/02/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "Praça", "333.333.333-33", 3));
	  teste1.add(Cliente.criaClienteBanco("Carol", LocalDate.parse("25/06/19432498", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "Travessa", "444.444.444-44", 4));
	  System.out.println(new ClienteDao().insereVariosTransacao(teste1));
	 
	         
	    

	    //Conexao teste1 = new Conexao();
	    //System.out.println(teste1.abreConexao());
	    
	    //System.out.println(teste.insere(Cliente.criaPessoaBanco("Maluco", LocalDate.now(), "Rua", "3232", 20)));
	    //System.out.println(teste.insere(Cliente.criaPessoaBanco("Leonardo", LocalDate.parse("26/01/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "Rua Blumenau", "111.111.222-33", 1)));
	    //System.out.println(teste.criaTabela());
	    //System.out.println(teste.pegaUltimoID());
	   
	    	    
	    }
	}

