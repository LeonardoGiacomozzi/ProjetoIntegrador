package edu.sit.controller.cadastro;


import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Produto;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;

public class ProdutoController {
	
	public static boolean cadastro() throws CadastroExeption {

		String nome = null;
		Integer categoriaId = null;
		Integer fornecedorId = null;
		Integer quantidade = null;
		Double valorUnitario = null;

		System.out.println("*****CADASTRO DE PRODUTO*****");

		categoriaId = UtilCadastro.pedeCategoria();
		nome = UtilCadastro.pedeNome();
		fornecedorId = UtilCadastro.pedeFornecedor();
		quantidade = UtilCadastro.pedeQuantidade();
		valorUnitario = UtilCadastro.pedeValorUnitario();

		try {
			Produto produto = Produto.criaProdutoBanco(nome, categoriaId, fornecedorId, quantidade, valorUnitario);
			System.out.println(new ProdutoDao().insere(produto) ? "Funcionario cadastrado com sucesso" : "Falha");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_PRODUTO);
		}

		return true;
	}

	public static boolean editar(Integer idProduto) throws EdicaoException{
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
				opcao = Leitor.leInteger();
				switch (opcao) {
				case 1:
					produtoBanco.setNome(UtilCadastro.pedeNome());

					break;
				case 2:
					produtoBanco.setCategoriaId(UtilCadastro.pedeCategoria());
					break;
				case 3:
					produtoBanco.setFornecedorId(UtilCadastro.pedeFornecedor());
					break;
				case 4:
					produtoBanco.setValorUnitario(UtilCadastro.pedeValorUnitario());
					break;
				case 0:
					try {

						System.out.println(
								new ProdutoDao().altera(produtoBanco) ? "Funcionario cadastrado com sucesso" : "Falha");
					} catch (DaoException e) {
						System.out.println(e.getMessage());
						throw new EdicaoException(EErroEdicao.ERRO_EDICAO_PRODUTO);
					}
					break;
				}
			}
		} catch (DaoException | ConexaoException | CadastroExeption | LeituraException e) {
			System.out.println("Não foi possivel buscat o produto informado\nErro " + e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_PRODUTO);
		}

		return true;

	}

	
	

	
	

	
}
