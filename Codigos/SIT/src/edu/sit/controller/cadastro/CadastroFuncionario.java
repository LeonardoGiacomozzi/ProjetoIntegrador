package edu.sit.controller.cadastro;


import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.leitura.LeituraEsception;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.ECargo;
import edu.sit.model.Funcionario;
import edu.sit.uteis.Leitor;

public class CadastroFuncionario {

	public boolean CadastraFuncionario() throws ConexaoException, CadastroExeption {

		String nome = null;
		ECargo cargo = null;
		String cpf = null;
		
		System.out.println("*****CADASTRO DE FUNCIONARIO*****");

		while (nome == null) {
			try {
				System.out.print("Nome:\t");
				nome = Leitor.leString();
			} catch (LeituraEsception e) {
				System.out.println(e.getMessage());
			}
		}
		
		while (cargo == null) {
			try {
				System.out.print("Cargo:\t");
				
				cargo = ECargo.values()[Leitor.leInteger()];
			} catch (LeituraEsception e) {
				System.out.println(e.getMessage());
			}
		}
		
		while (cpf== null) {
			try {
				System.out.print("Cpf:\t");
				cpf = Leitor.leCpf();
			} catch (LeituraEsception e) {
				System.out.println(e.getMessage());
			}
		}
		if (CadastroContato.cadastraContato()) {

			try {
				Funcionario funcionario =	Funcionario.criaFuncionarioBanco(nome, cpf, cargo, new ContatoDao().pegaUltimoID());
				System.out.println(new FuncionarioDao().insere(funcionario) ? "Funcionario cadastrado com sucesso" : "Falha");
			} catch (DaoException e) {
				throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_FUNCIONARIO);
			}
		}
		
		
		return true;
	}
}
