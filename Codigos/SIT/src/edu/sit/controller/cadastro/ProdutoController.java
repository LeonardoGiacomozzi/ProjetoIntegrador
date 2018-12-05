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
			System.out.println("\n**** EDIÇÃO DE PRODUTO ****");
			while (opcao != 0) {
				System.out.println(String.format("%-27s", "\n1 - Nome: ") + produtoBanco.getNome());
				System.out.println(String.format("%-26s", "2 - Categoria: ") + produtoBanco.getCategoria().getNome());
				System.out.println(String.format("%-26s", "3 - Fornecedor: ") + produtoBanco.getFornecedor().getNome());
				System.out.println(String.format("%-26s", "    CNPJ:") + produtoBanco.getFornecedor().getCNPJ());
				System.out.println(String.format("%-26s", "    Responsável:") + produtoBanco.getFornecedor().getPessoaResponsavel());
				System.out.println(String.format("%-26s", "    Telefone:") + produtoBanco.getFornecedor().getContato().getTelefone());
				System.out.println(String.format("%-26s", "    Email:") + produtoBanco.getFornecedor().getContato().getEmail());
				System.out.println(String.format("%-26s", "4 - Valor Unitário:") + "R$" + produtoBanco.getValorUnitario());
				System.out.println("\n0 - Finalizar");
				System.out.print("\nInforme a opção que deseja alterar: \t");
				opcao = Leitor.leInteger();
				switch (opcao) {
				case 1:
					produtoBanco.setNome(UtilCadastro.pedeNome("\nInforme o novo Nome: \t"));

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
								new ProdutoDao().altera(produtoBanco) ? "\nProduto alterado com SUCESSO!" : "Falha");
					} catch (DaoException e) {
						System.out.println(e.getMessage());
						throw new EdicaoException(EErroEdicao.ERRO_EDICAO_PRODUTO);
					}
					break;
				default:
					System.out.println("\nValor Inválido!\n\nSelecione uma das opções oferecidas: \t");
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
