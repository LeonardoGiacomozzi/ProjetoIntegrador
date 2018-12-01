package edu.sit.installer.run;

import edu.sit.bancodedados.conexao.Conexao;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.CategoriaDao;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.bancodedados.dao.UsuarioDao;
import edu.sit.bancodedados.dao.VendaDao;
import edu.sit.erro.instalacao.InstalacaoException;
import edu.sit.erro.propriedades.PropriedadesException;
import edu.sit.erros.dao.DaoException;
import edu.sit.installer.populador.Populador;
import edu.sit.propriedades.Configuracao;

public class Instalar {

	public static void main(String[] args) {
		System.out.println("***********************************************************************");
		System.out.println("*                                                                     *");
		System.out.println("*                          BEM VINDO AO SIT                           *");
		System.out.println("*                                                                     *");
		System.out.println("***********************************************************************\n");
		System.out.println("Preencha os dados corretamente para melhor funcionamento do sistema!!!");
		try {
			Configuracao.setPropriedades();
		} catch (PropriedadesException e1) {
			System.out.println(e1.getMessage());
			System.out.println("\nErro ao salvar as configura��es do sistema");
		}
		try {
			System.out.println(Conexao.CriaBanco() ? "\n\nBanco criado com SUCESSO!\n" : "fatal error");
		} catch (ConexaoException | DaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar o banco de dados");

		}
		try {
			System.out.println(new ContatoDao().criaTabela() ? "Tabela de Contato criada com SUCESSO!" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Contato");
		}
		try {
			System.out.println(
					new FornecedorDao().criaTabela() ? "Tabela de Fornecedor criada com SUCESSO!" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Fornecedor");
		}

		try {
			System.out.println(
					new CategoriaDao().criaTabela() ? "Tabela de Categoria criada com SUCESSO!" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Categoria");
		}
		try {
			System.out.println(new ProdutoDao().criaTabela() ? "Tabela de Produtos criada com SUCESSO!" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Produto");
		}
		try {
			System.out.println(new ClienteDao().criaTabela() ? "Tabela de Cliente criada com SUCESSO!" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Cliente");

		}
		try {
			System.out.println(
					new FuncionarioDao().criaTabela() ? "Tabela de Funcion�rio criada com SUCESSO!" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Funcionario");
		}
		try {
			System.out.println(new VendaDao().criaTabela() ? "Tabela de Venda criada com SUCESSO!" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Venda");
		}
		
		try {
			System.out.println(new UsuarioDao().criaTabela() ? "Tabela de Usu�rios criada com SUCESSO!" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Usuario");
		}
		try {
			System.out.println(Populador.cliente() ? "Populador de clientes OK..." : "n�oFoi");
			System.out.println(Populador.funcionario() ? "Populador de funcion�rios OK..." : "n�oFoi");
			System.out.println(Populador.categoria() ? "Populador de categorias OK..." : "n�oFoi");
			System.out.println(Populador.fornecedor() ? "Populador de fornecedor OK..." : "n�oFoi");
			System.out.println(Populador.produto() ? "Populador de produtos OK..." : "n�oFoi");
			System.out.println(Populador.venda() ? "Populador de vendas OK..." : "n�oFoi");
			System.out.println(Populador.usuario()? "Populador de �suarios OK..." : "n�oFoi");
			System.out.println("\n\nPronto! O sistema ja est� configurado e pronto para ser USADO!");
		} catch (InstalacaoException e) {
			System.out.println(e.getMessage());
			System.out.println("Erro ao popular o banco");
		}

	}

}
