package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.CategoriaDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Categoria;
import edu.sit.model.Fornecedor;
import edu.sit.model.Produto;
import edu.sit.uteis.Leitor;
import edu.sit.view.menu.LeituraException;

public class CadastroProduto {
	public boolean CadastraProduto() throws ConexaoException, CadastroExeption, DaoException {

		String nome = null;
		Integer categoriaId = null;
		Integer fornecedorId = null;
		Integer quantidade = null;
		Double valorUnitario = null;

		System.out.println("*****CADASTRO DE PRODUTO*****");

		categoriaId = pedeCategoria();
		nome = pedeNome();
		fornecedorId = pedeFornecedor();
		quantidade = pedeQuantidade();
		valorUnitario = pedeValorUnitario();

		try {
			Produto produto = Produto.criaProdutoBanco(nome, categoriaId, fornecedorId, quantidade, valorUnitario);
			System.out.println(new ProdutoDao().insere(produto) ? "Funcionario cadastrado com sucesso" : "Falha");
		} catch (DaoException e) {
			throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_FUNCIONARIO);
		}

		return true;
	}

	public boolean EditaProduto() {

		String nome = null;
		Integer categoriaId = null;
		Integer fornecedorId = null;
		Integer quantidade = null;
		Double valorUnitario = null;
		Integer opcao = null;
		System.out.println("*****EDITO DE PRODUTO*****");
		while (opcao == null) {
			System.out.println("\n\tSELECIONE O ITEM QUE DESEJA EDITAR\t:");
			System.out.println("\n\t\t1----------NOME");
			System.out.println("\n\t\t2----------CATEGORIA");
			System.out.println("\n\t\t3----------FORNECEDOR");
			System.out.println("\n\t\t4----------QUANTIDADE");
			System.out.println("\n\t\t4----------VALOR UNITARIO");
			
		}
		categoriaId = pedeCategoria();
		nome = pedeNome();
		fornecedorId = pedeFornecedor();
		quantidade = pedeQuantidade();
		valorUnitario = pedeValorUnitario();

		try {
			Produto produto = Produto.criaProdutoBanco(nome, categoriaId, fornecedorId, quantidade, valorUnitario);
			System.out.println(new ProdutoDao().insere(produto) ? "Funcionario cadastrado com sucesso" : "Falha");
		} catch (DaoException e) {
			throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_FUNCIONARIO);
		}

		return true;

		return false;

	}

	private Integer pedeCategoria() throws DaoException, ConexaoException, CadastroExeption {
		Integer categoriaId = null;

		while (categoriaId == null) {
			try {
				System.out.println("Categoria:\t");
				for (Categoria categoria : new CategoriaDao().consultaTodos()) {
					System.out.println("ID\t: " + categoria.getId() + "Nome\t: " + categoria.getNome());
				}
				System.out.println("0 --- CADASTRAR CATEGORIA");
				categoriaId = Leitor.leInteger();
				if (categoriaId == 0) {
					System.out.println(CadastroCategoria.cadastraCategoria() ? "Categoria cadastrada\n"
							: "Erro ao cadastrar categoria");
					categoriaId = new CategoriaDao().pegaUltimoID();
				}
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return categoriaId;

	}

	private String pedeNome() {
		String nome = null;

		while (nome == null) {
			try {
				System.out.print("Nome:\t");
				nome = Leitor.leString();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return nome;
	}

	private Integer pedeFornecedor() throws DaoException, ConexaoException, CadastroExeption {
		Integer fornecedorId = null;

		while (fornecedorId == null) {
			try {
				System.out.println("Fornecedor:\t");
				for (Fornecedor fornecedor : new FornecedorDao().consultaTodos()) {
					System.out.println("ID\t: " + fornecedor.getId() + "Nome\t: " + fornecedor.getNome());
				}
				System.out.println("0 --- CADASTRAR FORNECEDOR");
				fornecedorId = Leitor.leInteger();
				if (fornecedorId == 0) {
					System.out.println(CadastroFornecedor.CadastraFornecedor() ? "Fornecedor cadastrada\n"
							: "Erro ao cadastrar categoria");
					fornecedorId = new FornecedorDao().pegaUltimoID();
				}
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return fornecedorId;

	}

	private Integer pedeQuantidade() {

		Integer quantidade = null;

		while (quantidade == null) {
			try {
				System.out.print("Quantidade:\t");
				quantidade = Leitor.leInteger();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return quantidade;

	}

	private Double pedeValorUnitario() {

		Double valorUnitario = null;

		while (valorUnitario == null) {
			try {
				System.out.print("Valor Unitario:\t");
				valorUnitario = Leitor.leDouble();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}
		return valorUnitario;

	}
}
