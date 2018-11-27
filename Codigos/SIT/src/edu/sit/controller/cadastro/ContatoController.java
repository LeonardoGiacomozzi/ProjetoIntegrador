package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.visualizacao.EErroVisualizacao;
import edu.sit.erro.visualizacao.VisualizacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Contato;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;

public class ContatoController {

	public static boolean cadastro() throws CadastroException {
		String email = null;
		String tel = null;

		tel = UtilCadastro.pedeTelefone();
		email = UtilCadastro.pedeEmail();
		try {
			Contato contato = Contato.criaContato(tel, email);
			new ContatoDao().insere(contato);
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new CadastroException(EErroCadastro.ERRO_CADASTRO_CONTATO);
		}
		return true;
	}

	public static boolean editar(Integer codigo) throws EdicaoException {
		try {
			Contato contatoBanco = new ContatoDao().consulta(codigo);

			Integer opcao = 99;
			System.out.print("**** EDITOR DE CONTATO ****");
			while (opcao != 0) {
				System.out.println("\n\tSELECIONE O ITEM QUE DESEJA EDITAR\t:");
				System.out.println("\n\t\t1----------TELEFONE\t" + contatoBanco.getTelefone());
				System.out.println("\n\t\t2----------E-MAIL\t" + contatoBanco.getEmail());
				System.out.println("\n\t\t0----------FINALIZAR");
				System.out.println("\n\n\t\t---:");
				opcao = Leitor.leInteger();
				switch (opcao) {
				case 1:
					contatoBanco.setTelefone(UtilCadastro.pedeTelefone());
					break;
				case 2:
					contatoBanco.setEmail(UtilCadastro.pedeEmail());
					break;
				case 0:
					try {
						new ContatoDao().altera(contatoBanco);

					} catch (DaoException e) {
						System.out.println(e.getMessage());
						throw new EdicaoException(EErroEdicao.ERRO_EDICAO_CONTATO);
					}
					break;
				default:
					System.out.println("Valor Invalido\nSelecione uma das opções oferecidas:");
					break;
				}
			}
			return true;
		} catch (DaoException | ConexaoException | EdicaoException | LeituraException e) {
			System.out.println(e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_CONTATO);
		}
		
	}

	public static boolean visualizar() throws VisualizacaoException {

		try {
			for (Contato contato : new ContatoDao().consultaTodos()) {
				System.out.println("#" + contato.getId() + " ----------- " + contato.getTelefone()+ " ----------- " + contato.getEmail());
			}
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new VisualizacaoException(EErroVisualizacao.ERRO_BUSCA_CONTATOS);
		}
	}

}
