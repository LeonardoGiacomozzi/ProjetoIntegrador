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
		//		"dd/MM/yyyy")), "Rua", "111.111.111-11", 1)));
		
		
		
		//Exclui apenas 1 linha ou varias....
		//System.out.println(teste.exclui(3));
		//System.out.println(teste.exclui(1));
	}
}