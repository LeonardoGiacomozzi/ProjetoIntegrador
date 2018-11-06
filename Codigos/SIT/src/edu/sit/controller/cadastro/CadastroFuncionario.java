package edu.sit.controller.cadastro;

import java.time.LocalDate;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.model.ECargo;
import edu.sit.uteis.Leitor;
import edu.sit.view.menu.LeituraException;

public class CadastroFuncionario {

	public boolean CadastraFuncionario() throws ConexaoException, CadastroExeption {

		String nome = null;
		ECargo cargo = null;

		System.out.println("*****CADASTRO DE FUNCIONARIO*****");

		while (nome == null) {
			try {
				System.out.print("Nome:\t");
				nome = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		
		while (cargo == null) {
			try {
				System.out.print("Endereço:\t");
				cargo = Leitor.leInteger();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return true;
	}
}
