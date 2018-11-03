package edu.sit.controller.cadastro;

import java.time.LocalDate;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.DaoException;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.model.Cliente;
import edu.sit.model.Contato;
import edu.sit.uteis.Leitor;
import edu.sit.view.menu.LeituraException;

public class CadastroCliente {
	
	public boolean CadastraCliente() throws ConexaoException, CadastroExeption {
		
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
		int idContato=new ContatoDao().pegaUltimoID();
		Cliente cliente = Cliente.criaClienteBanco(nome,dataNascimento , endereco, cpf, idContato);
		System.out.println(new ClienteDao().insere(cliente)?"Cliente cadastrado com sucesso":"Faha");
	} catch (DaoException e) {
		throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_CLIENTE);
	}	
		return true;
	}

}
