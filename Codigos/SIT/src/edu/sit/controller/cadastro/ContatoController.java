package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Contato;
import edu.sit.uteis.Leitor;

public class ContatoController {

	public static boolean cadastraContato() throws CadastroExeption, ConexaoException {
		String email = null;
		String tel = null;
		System.out.print("*****CADASTRO DE CONTATO*****");

		while (tel == null) {
			try {
				System.out.print("Telefone:\t");
				tel = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		while (email == null) {
			try {
				System.out.print("Email:\t");
				email = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		try {
			Contato contato = Contato.criaContato(tel, email);
			new ContatoDao().insere(contato);
		} catch (DaoException e) {
			throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_CONTATO);
		}
		return false;
	}

	public static Contato editar(Integer contatoid) {
		// TODO Auto-generated method stub
		return null;
	}
}
