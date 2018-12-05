package edu.sit.controller.cadastro;


import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.CategoriaDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.controller.ControllerException;
import edu.sit.erro.controller.EErroController;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Produto;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;

public class ProdutoController {
	
	public static boolean cadastro() throws CadastroException {

		String nome = null;
		Integer categoriaId = null;
		Integer fornecedorId = null;
		Integer quantidade = null;
		Double valorUnitario = null;

		nome = UtilCadastro.pedeNome("Nome: \t");
		quantidade = UtilCadastro.pedeQuantidade();
		valorUnitario = UtilCadastro.pedeValorUnitario();
		categoriaId = UtilCadastro.pedeCategoria();
		try {
			while(new CategoriaDao().consulta(categoriaId)== null) {
				System.out.println("Categoria Inexistente");
				System.out.println("Informe uma nova categoria");
				categoriaId = UtilCadastro.pedeCategoria();
			}
		} catch (DaoException | ConexaoException e1) {
			System.out.println(e1.getMessage());
		}
		fornecedorId = UtilCadastro.pedeFornecedor();
		try {
			while(new FornecedorDao().consulta(fornecedorId) == null) {
				System.out.println("Fornecedor Inexistente");
				System.out.println("Informe uma novo Fornecedor Válido");
				fornecedorId = UtilCadastro.pedeFornecedor();
			}
		} catch (DaoException | ConexaoException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			Produto produto = Produto.criaProdutoBanco(nome, categoriaId, fornecedorId, quantidade, valorUnitario);
			System.out.print(new ProdutoDao().insere(produto) ? "\nProduto cadastrado com SUCESSO!\n\n" : "\nFalha\n");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new CadastroException(EErroCadastro.ERRO_CADASTRO_PRODUTO);
		}

		return true;
	}

	public static boolean editar(Integer idProduto) throws EdicaoException{
		Produto produtoBanco = null;
		try {
			produtoBanco = new ProdutoDao().consultaCompleta(idProduto);

			Integer opcao = 99;
			System.out.println("**** EDITAR DE PRODUTO ****");
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
					produtoBanco.setNome(UtilCadastro.pedeNome("Nome"));

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
				default:
					System.out.println("Valor Invalido\nSelecione uma das opções oferecidas:");
					break;
				}
			}
		} catch (DaoException | ConexaoException | CadastroException | LeituraException e) {
			System.out.println("Não foi possivel buscar o produto informado\nErro " + e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_PRODUTO);
		}

		return true;

	}
	
	public static boolean reporEstoque(Integer codigo,Integer quantidade) throws ControllerException {
		Produto produto = null;
		try {
			produto = new ProdutoDao().consulta(codigo);
		} catch (DaoException | ConexaoException e) {
			throw new ControllerException(EErroController.ERRO_BUSCAR_PRODUTO);
		}
		
		if (produto!=null) {
			produto.setQuantidade(produto.getQuantidade()+quantidade);
			try {
				new ProdutoDao().altera(produto);
			} catch (DaoException | ConexaoException a) {
				System.out.println(a.getMessage());
			}
		}else {
			return false;
		}
		return true;
	}
}
