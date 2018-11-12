package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Contato;
import edu.sit.uteis.Leitor;
import edu.sit.view.menu.LeituraException;

public class CadastroCategoria {

	public static boolean cadastraCategoria() throws CadastroExeption, ConexaoException {
		String nome = null;
		System.out.print("*****CADASTRO DE CATEGORIA*****");

		while (nome == null) {
			try {
				System.out.print("Telefone:\t");
				tel = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
				try {
		} catch (DaoException e) {
			throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_CONTATO);
		}
		return false;
	}
}
