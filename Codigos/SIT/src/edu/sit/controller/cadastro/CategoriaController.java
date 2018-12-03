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
			new CategoriaDao().insere(categoria);

		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new CadastroException(EErroCadastro.ERRO_CADASTRO_CATEGORIA);
		}
		return true;
	}

	public static boolean editar(Integer codigo) throws EdicaoException {
		try {
			Categoria categoriaBanco = new CategoriaDao().consulta(codigo);

			System.out.print("**** EDIÇÃO DE CATEGORIA ****");
			Integer opcao = 99;
			while (opcao != 0) {
				System.out.println("\n\tSELECIONE O ITEM QUE DESEJA EDITAR\t:");
				System.out.println("\n\t\t1----------NOME\t" + categoriaBanco.getNome());
				System.out.println("\n\t\t0----------FINALIZAR");
				System.out.println("\n\n\t\t---:");
				opcao = Leitor.leInteger();

				switch (opcao) {
				case 1:
					categoriaBanco.setNome(UtilCadastro.pedeNome("Nome"));
					break;

				case 0:

					try {
						new CategoriaDao().altera(categoriaBanco);

					} catch (DaoException e) {
						System.out.println(e.getMessage());
						throw new EdicaoException(EErroEdicao.ERRO_EDICAO_CATEGORIA);
					}
					break;
				default:
					System.out.println("Valor Invalido\nSelecione uma das opções oferecidas:");
					break;

				}

			}
			return true;
		} catch (DaoException | ConexaoException | LeituraException e) {
			System.out.println(e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_CATEGORIA);
		}

	}
	
}
