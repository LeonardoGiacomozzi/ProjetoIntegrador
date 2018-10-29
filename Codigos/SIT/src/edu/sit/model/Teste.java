package edu.sit.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.DaoException;

public class Teste {
	 
	 
	    public static void main(String[] args) throws DaoException, ConexaoException {
//	      NotaFiscal nota = NotaFiscal.criaNotaFiscal(Funcionario.criaFuncionario("teste",ECargo.VENDEDOR), Cliente.criaPessoa("TesteCliente", new Date("10/10/1998")), new ArrayList<Produto>(Produto.criaProdutoSemFornecedor("xpto","xprr"
//	    		  ,(Integer)10, (Double) 100)));
//	      GeraAquivoNotaFiscal.geraArquivo(nota, "c:/lixo/teste.txt");
	    	
	    	        Date dt = new Date ();
	    	        
	    	        
	    	        System.out.println (dt);
	    	        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss.SSS");
	    	        df.setTimeZone (TimeZone.getTimeZone ("GMT"));
	    	        System.out.println (df.format (dt));
	    	        
	  ClienteDao teste = new ClienteDao();
	  //System.out.println(teste.criaTabela());
	 // System.out.println(teste.insere(Cliente.criaPessoaBanco("Joao", new Date(), "Rua", "111.111.111-11", 1)));
	 // System.out.println(teste.insere(Cliente.criaPessoaBanco("Maria", new Date(), "Avenida", "222.222.222-22", 2)));
	 // System.out.println(teste.insere(Cliente.criaPessoaBanco("Roberto", new Date(), "Praça", "333.333.333-33", 3)));
	 // System.out.println(teste.insere(Cliente.criaPessoaBanco("Larissa", new Date(), "Travessa", "444.444.444-44", 4)));
	  
	 //System.out.println(teste.insere(Cliente.criaPessoaBanco("Manoela", new java.sql.Date(10051998), "Travessa", "555.555.555-55", 6)));
	  //System.out.println(teste.consulta(5));
	 
	 //List<Cliente> teste1 = new ArrayList<>();
	 //teste1.add(Cliente.criaPessoaBanco("Joao", new Date(), "Rua", "111.111.111-11", 1));
	 //teste1.add(Cliente.criaPessoaBanco("Maria", new Date(), "Avenida", "222.222.222-22", 2));
	 //teste1.add(Cliente.criaPessoaBanco("Roberto", new Date(), "Praça", "333.333.333-33", 3));
	 //teste1.add(Cliente.criaPessoaBanco("Larissa", new Date(), "Travessa", "444.444.444-44", 4));
	 //System.out.println(teste1);
	
	 System.out.println(teste.exclui(6));
	  
	 
	 
	    	        
	    	        ///ClienteDao teste = new ClienteDao();
	   //Cliente cliente = teste.consulta(1);
	   //System.out.println(cliente);
	   
	 
	   
	    	    
	    }
	}

