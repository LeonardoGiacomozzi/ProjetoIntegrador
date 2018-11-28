package edu.sit.installer.run;

import edu.sit.bancodedados.conexao.Conexao;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.erro.propriedades.PropriedadesException;
import edu.sit.erros.dao.DaoException;
import edu.sit.propriedades.Configuracao;

public class Instalar {

	public static void main(String[] args) {
		
		System.out.println("***************************BEM VINDO AO SIT***************************");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Preencha os dados corretamente para o correto funcionamento do sistema");
		try {
			Configuracao.setPropriedades();
		} catch (PropriedadesException e1) {
			System.out.println(e1.getMessage());
			System.out.println("\nErro ao salvar as configurações do sistema");
		}
		try {
			System.out.println(Conexao.CriaBanco()?"Banco criado com sucesso":"fatal error");
		} catch (ConexaoException | DaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar o banco de dados");
			
		}
		try {
			System.out.println(new ContatoDao().criaTabela()?"Tabela de Contato criada com sucesso":"fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Contato");
		}
		try {
			System.out.println(new FornecedorDao().criaTabela()?"Tabela de Fornecedor criada com sucesso":"fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Fornecedor");
		}
	}

}
