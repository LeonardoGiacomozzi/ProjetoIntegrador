package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Fornecedor;
import edu.sit.uteis.Leitor;

public class FornecedorController {
	

	public static boolean CadastraFornecedor() throws ConexaoException, CadastroExeption {
		String nome = null;
		String cnpj = null;
		String pessoaResponsavel = null;
		System.out.println("*****CADASTRO DE FOENECEDOR*****");

		while (nome == null) {
			try {
				System.out.print("Nome:\t");
				nome = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}

		while (cnpj == null) {
			try {
				System.out.print("Cnpj:\t");
				cnpj = Leitor.leCnpj();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		while (pessoaResponsavel == null) {
			try {
				System.out.print("Nome da pessoa responsavel:\t");
				pessoaResponsavel = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		if (ContatoController.cadastraContato()) {

			try {
				Fornecedor fornecedor = Fornecedor.criaFornecedorFull(nome, cnpj, pessoaResponsavel,
						new ContatoDao().pegaUltimoID());
				System.out.println(
						new FornecedorDao().insere(fornecedor) ? "Fornecedor cadastrado com sucesso" : "Falha");
			} catch (DaoException e) {
				throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_FORNECEDOR);
			}
		}
		return true;
	}
}
