package edu.sit.installer.run;

import edu.sit.bancodedados.conexao.Conexao;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.CategoriaDao;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.bancodedados.dao.NotaFiscalDao;
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
			System.out.println(Conexao.CriaBanco() ? "Banco criado com sucesso" : "fatal error");
		} catch (ConexaoException | DaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar o banco de dados");

		}
		try {
			System.out.println(new ContatoDao().criaTabela() ? "Tabela de Contato criada com sucesso" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Contato");
		}
		try {
			System.out.println(
					new FornecedorDao().criaTabela() ? "Tabela de Fornecedor criada com sucesso" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Fornecedor");
		}

		try {
			System.out.println(
					new CategoriaDao().criaTabela() ? "Tabela de Categoria criada com sucesso" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Categoria");
		}
		try {
			System.out.println(new ProdutoDao().criaTabela() ? "Tabela de Produtos criada com sucesso" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Produto");
		}
		try {
			System.out.println(new ClienteDao().criaTabela() ? "Tabela de Cliente criada com sucesso" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Cliente");

		}
		try {
			System.out.println(
					new FuncionarioDao().criaTabela() ? "Tabela de Funcionario criada com sucesso" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Funcionario");
		}
		try {
			System.out.println(new VendaDao().criaTabela() ? "Tabela de Venda criada com sucesso" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Venda");
		}
		try {
			System.out.println(
					new NotaFiscalDao().criaTabela() ? "Tabela de Nota Fiscal criada com sucesso" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Nota Fiscal");
		}

		try {
			System.out.println(new UsuarioDao().criaTabela() ? "Tabela de Usuarios criada com sucesso" : "fatal error");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			System.out.println("\nErro ao criar a tabela de Usuario");
		}
		try {
			System.out.println(Populador.cliente() ? "Populador de clienes OK..." : "nãoFoi");
			System.out.println(Populador.funcionario() ? "Populador de funcionarios OK..." : "nãoFoi");
			System.out.println(Populador.categoria() ? "Populador de categorias OK..." : "nãoFoi");
			System.out.println(Populador.fornecedor() ? "Populador de fornecedor OK..." : "nãoFoi");
			System.out.println(Populador.produto() ? "Populador de produtos OK..." : "nãoFoi");
			System.out.println(Populador.venda() ? "Populador de vendas OK..." : "nãoFoi");
		} catch (InstalacaoException e) {
			System.out.println(e.getMessage());
			System.out.println("Erro ao popuar o banco");
		}

	}

}
