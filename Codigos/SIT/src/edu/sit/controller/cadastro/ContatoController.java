package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
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
			System.out.print("\n**** EDIÇÃO DE CONTATO ****\n");
			while (opcao != 0) {
				System.out.println(String.format("%-27s", "\n1 - Telefone: ") + contatoBanco.getTelefone());
				System.out.println(String.format("%-26s", "2 - Email: ") + contatoBanco.getEmail());
				System.out.println("\n0 - Finalizar");
				System.out.print("\nInforme a opção que deseja alterar: \t");
				try {
					opcao = Leitor.leInteger();
				} catch (LeituraException e1) {
					System.out.println(e1.getMessage());
				}
				switch (opcao) {
				case 1:
					contatoBanco.setTelefone(UtilCadastro.pedeTelefone());
					break;
				case 2:
					contatoBanco.setEmail(UtilCadastro.pedeEmail());
					break;
				case 0:
					try {
						System.out.println(new ContatoDao().altera(contatoBanco) ? "\nContato alterado com SUCESSO!" : "\nFalha");

					} catch (DaoException e) {
						System.out.println(e.getMessage());
						throw new EdicaoException(EErroEdicao.ERRO_EDICAO_CONTATO);
					}
					break;
				default:
					System.out.println("\nValor Inválido!\n\nSelecione uma das opções oferecidas: \t");
					break;
				}
			}
			return true;
		} catch (DaoException | ConexaoException | EdicaoException e) {
			System.out.println(e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_CONTATO);
		}
		
	}
}
