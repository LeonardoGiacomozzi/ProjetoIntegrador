package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.CategoriaDao;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Categoria;
import edu.sit.model.ECargo;
import edu.sit.model.Fornecedor;
import edu.sit.model.Funcionario;
import edu.sit.model.Produto;
import edu.sit.uteis.Leitor;
import edu.sit.view.menu.LeituraException;

public class CadastroProduto {
	public boolean CadastraProduto() throws ConexaoException, CadastroExeption {

		String nome = null;
		Integer categoriaId = null;
		Integer fornecedorId = null;
		Integer quantidade = null;
		Double valorUnitario = null;
		
		System.out.println("*****CADASTRO DE PRODUTO*****");

		while (categoriaId == null) {
			try {
				System.out.println("Categoria:\t");
				for (Categoria categoria : new CategoriaDao().consultaTodos()) {
					System.out.println("ID\t: "+categoria.getId()+"Nome\t: "+categoria.getNome());
				}
				System.out.println("0 --- CADASTRAR CATEGORIA");
				categoriaId = Leitor.leInteger();
				if (categoriaId == 0) {
					System.out.println(CadastroCategoria.cadastraCategoria()?"Categoria cadastrada\n":"Erro ao cadastrar categoria");
					categoriaId = new CategoriaDao().pegaUltimoID();
				}
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		
		while (nome == null) {
			try {
				System.out.print("Nome:\t");
				nome = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		
		while (fornecedorId== null) {
			try {
				System.out.println("Fornecedor:\t");
				for (Fornecedor fornecedor : new FornecedorDao().consultaTodos()) {
					System.out.println("ID\t: "+fornecedor.getId()+"Nome\t: "+fornecedor.getNome());
				}
				System.out.println("0 --- CADASTRAR FORNECEDOR");
				fornecedorId = Leitor.leInteger();
				if (fornecedorId == 0) {
					System.out.println(CadastroFornecedor.CadastraFornecedor() ?"Categoria cadastrada\n":"Erro ao cadastrar categoria");
					categoriaId = new CategoriaDao().pegaUltimoID();
				}
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		while (quantidade == null) {
			try {
				System.out.print("Quantidade:\t");
				quantidade = Leitor.leInteger();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		
		while (valorUnitario == null) {
			try {
				System.out.print("Valor Unitario:\t");
				valorUnitario = Leitor.leDouble();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		

		try {
			Produto produto =	Produto.criaProdutoBanco(nome, categoria, quatidade, valorUnitario, fornecedor);
			System.out.println(new ProdutoDao().insere(produto) ? "Funcionario cadastrado com sucesso" : "Falha");
		} catch (DaoException e) {
			throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_FUNCIONARIO);
		}
	
		
		return true;
	}
}
