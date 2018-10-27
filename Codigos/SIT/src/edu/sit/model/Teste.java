package edu.sit.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Teste {
	 
	 
	    public static void main(String[] args) {
//	      NotaFiscal nota = NotaFiscal.criaNotaFiscal(Funcionario.criaFuncionario("teste",ECargo.VENDEDOR), Cliente.criaPessoa("TesteCliente", new Date("10/10/1998")), new ArrayList<Produto>(Produto.criaProdutoSemFornecedor("xpto","xprr"
//	    		  ,(Integer)10, (Double) 100)));
//	      GeraAquivoNotaFiscal.geraArquivo(nota, "c:/lixo/teste.txt");
	    	
	    	        Date dt = new Date ();
	    	        
	    	        
	    	        System.out.println (dt);
	    	        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss.SSS");
	    	        df.setTimeZone (TimeZone.getTimeZone ("GMT"));
	    	        System.out.println (df.format (dt));
	    	    
	    }
	}

