package edu.sit.controller.cadastro;


import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.CategoriaDao;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Categoria;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;

public class CategoriaController {

	public static boolean cadastro() throws CadastroException {
		String nome = null;
		System.out.print("\n**** CADASTRO DE CATEGORIA ****\n");
		nome = UtilCadastro.pedeNome("Nome: \t");
		try {
			Categoria categoria = Categoria.criaCategoria(nome);
			System.out.print(new CategoriaDao().insere(categoria) ? "\nCategoria cadastrada com SUCESSO!\n" : "\nFalha\n");
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new CadastroException(EErroCadastro.ERRO_CADASTRO_CATEGORIA);
		}
		return true;
	}

	public static boolean editar(Integer codigo) throws EdicaoException {
		try {
			Categoria categoriaBanco = new CategoriaDao().consulta(codigo);

			Integer opcao = 99;
			while (opcao != 0) {
				System.out.println(String.format("%-21s", "\n1 - Nome") + "[" + categoriaBanco.getNome() + "]");
				System.out.println("0 - Finalizar/Sair");
				System.out.print("\nInforme a opção que deseja alterar: \t");
				try {
					opcao = Leitor.leInteger();
				} catch (LeituraException e) {
					System.out.println(e.getMessage());
				}

				switch (opcao) {
				case 1:
					categoriaBanco.setNome(UtilCadastro.pedeNome("\nDigite o novo Nome: \t"));
					break;

				case 0:

					try {
						System.out.print(new CategoriaDao().altera(categoriaBanco) ? "\nCategoria alterada com SUCESSO!" : "\nFalha");
					} catch (DaoException e) {
						System.out.println(e.getMessage());
						throw new EdicaoException(EErroEdicao.ERRO_EDICAO_CATEGORIA);
					}
					break;
				default:
					System.out.println("\nValor Inválido!\nSelecione uma das opções oferecidas: \t");
					break;

				}

			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_CATEGORIA);
		}

	}
	
}
