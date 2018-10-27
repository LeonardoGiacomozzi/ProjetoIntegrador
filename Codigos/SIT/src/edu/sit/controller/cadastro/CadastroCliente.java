package edu.sit.controller.cadastro;

import edu.sit.model.Cliente;
import edu.sit.view.menu.Leitura;

public class CadastroCliente {
	
	public boolean CadastraCliente() {
		
		System.out.println("*****CADASTRO DE CLIENTE*****");
		System.out.print("Nome:\t");
		Leitura.leitor(); 
		return true;
	}

}
