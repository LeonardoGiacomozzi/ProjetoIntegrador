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
	  System.out.println(teste.consultaTodos());
	  
	    	        
	    	        ///ClienteDao teste = new ClienteDao();
	   //Cliente cliente = teste.consulta(1);
	   //System.out.println(cliente);
	   
	 
	   
	    	    
	    }
	}

