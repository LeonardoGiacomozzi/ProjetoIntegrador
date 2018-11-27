package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Fornecedor;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;

public class FornecedorController {
	

	public static boolean cadastro() throws CadastroException {
		String nome = null;
		String cnpj = null;
		String pessoaResponsavel = null;
		System.out.println("\n*** CADASTRO DE FORNECEDOR ***");

		nome = UtilCadastro.pedeNome();
		cnpj = UtilCadastro.pedeCnpj();
		pessoaResponsavel = UtilCadastro.pedePessoaResponsavel();
		
		if (ContatoController.cadastro()) {

			try {
				Fornecedor fornecedor = Fornecedor.criaFornecedorFull(nome, cnpj, pessoaResponsavel,
						new ContatoDao().pegaUltimoID());
				System.out.println(
						new FornecedorDao().insere(fornecedor) ? "\nFornecedor cadastrado com SUCESSO!\n" : "\nFalha\n");
			} catch (DaoException | ConexaoException e) {
				System.out.println(e.getMessage());
				throw new CadastroException(EErroCadastro.ERRO_CADASTRO_FORNECEDOR);
			}
		}
		return true;
	}
	
	public static boolean editar(Integer codigo) throws EdicaoException {
		try {
			Fornecedor fornecedorBanco = new FornecedorDao().consulta(codigo);

			System.out.print("*****EDITOR DE CATEGORIA*****");
			Integer opcao = 99;
			while (opcao != 0) {
				System.out.println("\n\tSELECIONE O ITEM QUE DESEJA EDITAR\t:");
				System.out.println("\n\t\t1----------NOME DO FORNECEDOR\t\t" + fornecedorBanco.getNome());
				System.out.println("\n\t\t2----------NOME DA PESSOA RESPONSAVE\t" + fornecedorBanco.getPessoaResponsavel());
				System.out.println("\n\t\t3----------NOME DA PESSOA RESPONSAVE\t" + fornecedorBanco.getContato().toString());
				System.out.println("\n\t\t0----------FINALIZAR");
				System.out.println("\n\n\t\t---:");
				opcao = Leitor.leInteger();

				switch (opcao) {
				case 1:
					fornecedorBanco.setNome(UtilCadastro.pedeNome());
					break;
				case 2:
					fornecedorBanco.setPessoaResponsavel(UtilCadastro.pedePessoaResponsavel());
					break;
				case 3:
					ContatoController.editar(fornecedorBanco.getContatoid());
					break;

				case 0:

					try {
						new FornecedorDao().altera(fornecedorBanco);

					} catch (DaoException e) {
						System.out.println(e.getMessage());
						throw new EdicaoException(EErroEdicao.ERRO_EDICAO_FORNECEDOR);
					}
					break;
				default:
					System.out.println("Valor Invalido\nSelecione uma das opções oferecidas:");
					break;

				}

			}
		} catch (DaoException | ConexaoException | LeituraException e) {
			System.out.println(e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_CATEGORIA);
		}
		return true;

	}

	public static boolean visualizar() throws VisualizacaoException {

		try {
			for (Fornecedor fornecedor : new FornecedorDao().consultaTodos()) {
				System.out.println("#" + fornecedor.getId() + " ----------- " + fornecedor.getNome()+ " ----------- " + fornecedor.getCNPJ());
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_FORNECEDORES);
		}
	}

}
