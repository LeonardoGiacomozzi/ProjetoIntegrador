package edu.sit.controller.cadastro;

import java.util.Date;

import edu.sit.uteis.Leitor;
import edu.sit.view.menu.LeituraException;

public class CadastroCliente {
	
	public boolean CadastraCliente() {
		
		String nome;
		Date dataNascimento;
		System.out.println("*****CADASTRO DE CLIENTE*****");
		System.out.print("Nome:\t");
		try {
			nome = Leitor.leString();
		} catch (LeituraException e) {
			System.out.println(e.getMessage());
		}
		try {
			dataNascimento= new Date(Leitor.leString());
		} catch (LeituraException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

}
