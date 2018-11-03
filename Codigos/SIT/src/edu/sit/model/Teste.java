package edu.sit.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.DaoException;

public class Teste {	 
	public static void main(String[] args) throws DaoException, ConexaoException {
		
		//Cria Tabela Banco
		ClienteDao teste = new ClienteDao();
		try {
			//System.out.println(teste.criaTabela());
		//Exclui tabela
			//System.out.println(teste.excluiTabela()); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		//Insere 1 por 1
		//System.out.println(teste.insere(Cliente.criaClienteBanco("Maria", LocalDate.parse("25/06/1998", DateTimeFormatter.ofPattern(
		//	"dd/MM/yyyy")), "Rua", "111.111.111-11", 1)));
		
		//Insere Varios por Lista
		//List<Cliente> lcliente = new ArrayList<>();
		//lcliente.add(Cliente.criaClienteBanco("Roberto", LocalDate.parse("12/09/1984", DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
		//		"Avenida", "222.222.222-22", 2));
		//lcliente.add(Cliente.criaClienteBanco("Larissa", LocalDate.parse("01/12/1991", DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
		//		"Travessa", "333.333.333-33", 3));
		//lcliente.add(Cliente.criaClienteBanco("Carol", LocalDate.parse("13/02/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
		//		"Beco", "444.444.444-44", 4));
		//System.out.println(teste.insereVarios(lcliente));
		
		
		
		
		//Consulta por id
		//System.out.println(teste.consulta(1));
		
		//Consulta todos
		//System.out.println(teste.consultaTodos());
		
		
		
		
		//Exclui apenas 1 linha ou varias....
		//System.out.println(teste.exclui(7,8,9,10));
		
		
		
		ContatoDao teste1 = new ContatoDao();
		teste1.criaTabela();
	}
}