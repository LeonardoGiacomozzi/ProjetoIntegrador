package edu.sit.controller.cadastro;

import java.time.LocalDate;

import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.model.Contato;
import edu.sit.uteis.Leitor;
import edu.sit.view.menu.LeituraException;

public class CadastroCliente {
	
	public boolean CadastraCliente() {
		
		String nome = null;
		LocalDate dataNascimento = null;
		String endereco = null;
		String cpf = null;
		String email = null;
		String tel = null;


		System.out.println("*****CADASTRO DE CLIENTE*****");
	
	while (nome == null) {
		try {
			System.out.print("Nome:\t");
			nome = Leitor.leString();
		} catch (LeituraException e) {
			System.out.println(e.getMessage());
		}
	}
	while (dataNascimento == null) {
		try {
			System.out.print("Data de Nascimento:\t");
			dataNascimento= Leitor.leData();
		} catch (LeituraException e) {
			System.out.println(e.getMessage());
		}
	}
	while (endereco == null) {
		try {
			System.out.print("Endereço:\t");
			endereco = Leitor.leString();
		} catch (LeituraException e) {
			System.out.println(e.getMessage());
		}
	}
	while (cpf== null) {
		try {
			System.out.print("Cpf:\t");
			endereco = Leitor.leString();
		} catch (LeituraException e) {
			System.out.println(e.getMessage());
		}
	}
	while (tel == null) {
		try {
			System.out.print("Telefone:\t");
			endereco = Leitor.leString();
		} catch (LeituraException e) {
			System.out.println(e.getMessage());
		}
	}
	while (email == null) {
		try {
			System.out.print("Email:\t");
			endereco = Leitor.leString();
		} catch (LeituraException e) {
			System.out.println(e.getMessage());
		}
	}
	
	try {
		Contato contato = Contato.criaContato(tel, email);
		 new ContatoDao().insere(contato);
	}	catch (Exception e) {
		// TODO: handle exception
	}	
		return true;
	}

}
