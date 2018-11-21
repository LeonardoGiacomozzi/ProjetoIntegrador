package edu.sit.controller.cadastro;


import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.CategoriaDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraEsception;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Categoria;
import edu.sit.model.Fornecedor;
import edu.sit.model.Produto;
import edu.sit.uteis.Leitor;

public class ProdutoController {
	
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
			throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_PRODUTO);
		}

		return true;
	}

	public boolean EditaProduto(Integer idProduto) throws EdicaoException, CadastroExeption {
		Produto produtoBanco = null;
		try {
			produtoBanco = new ProdutoDao().consultaCompleta(idProduto);

			Integer opcao = 99;
			System.out.println("*****EDITO DE PRODUTO*****");
			while (opcao != 0) {
				System.out.println("\n\tSELECIONE O ITEM QUE DESEJA EDITAR\t:");
				System.out.println("\n\t\t1----------NOME\t"+produtoBanco.getNome());
				System.out.println("\n\t\t2----------CATEGORIA\t"+produtoBanco.getCategoria());
				System.out.println("\n\t\t3----------FORNECEDOR\t"+produtoBanco.getFornecedor());
				System.out.println("\n\t\t4----------VALOR UNITARIO\tR$ "+produtoBanco.getValorUnitario());
				System.out.println("\n\t\t0----------FINALIZAR");
				System.out.println("\n\n\t\t---:");

				switch (opcao) {
				case 1:
					produtoBanco.setNome(pedeNome());

					break;
				case 2:
					produtoBanco.setCategoriaId(pedeCategoria());
					break;
				case 3:
					produtoBanco.setFornecedorId(pedeFornecedor());
					break;
				case 4:
					produtoBanco.setValorUnitario(pedeValorUnitario());
					break;
				case 0:
					try {

						System.out.println(
								new ProdutoDao().altera(produtoBanco) ? "Funcionario cadastrado com sucesso" : "Falha");
					} catch (DaoException e) {
						throw new EdicaoException(EErroEdicao.ERRO_EDICAO_PRODUTO);
					}
					break;
				}
			}
		} catch (DaoException | ConexaoException e) {
			System.out.println("Não foi possivel buscat o produto informado\nErro " + e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_PRODUTO);
		}

		return true;

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
					System.out.println(CategoriaController.cadastraCategoria() ? "Categoria cadastrada\n"
							: "Erro ao cadastrar categoria");
					categoriaId = new CategoriaDao().pegaUltimoID();
				}
			} catch (LeituraEsception e) {
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
			} catch (LeituraEsception e) {
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
					System.out.println(FornecedorController.CadastraFornecedor() ? "Fornecedor cadastrada\n"
							: "Erro ao cadastrar categoria");
					fornecedorId = new FornecedorDao().pegaUltimoID();
				}
			} catch (LeituraEsception e) {
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
			} catch (LeituraEsception e) {
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
			} catch (LeituraEsception e) {
				System.out.println(e.getMessage());
			}
		}
		return valorUnitario;

	}
}
