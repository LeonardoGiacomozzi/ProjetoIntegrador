package edu.sit.controller.cadastro;

import java.time.LocalDate;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Cliente;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;

public class ClienteController {

	public static boolean cadastro() throws CadastroException {

		String nome = null;
		String cpf = null;
		String endereco = null;
		LocalDate dataNascimento = null;

		nome = UtilCadastro.pedeNome();
		cpf = UtilCadastro.pedeCpf();
		endereco = UtilCadastro.pedeEndereco();
		dataNascimento = UtilCadastro.pedeDataNascimento();

		try {
			if (ContatoController.cadastraContato()) {

				try {
					Cliente cliente = Cliente.criaClienteBanco(nome, dataNascimento, endereco, cpf,
							new ContatoDao().pegaUltimoID());
					System.out.println(new ClienteDao().insere(cliente) ? "\nCliente cadastrado com SUCESSO!\n" : "\nFalha\n");
				} catch (DaoException e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (ConexaoException e) {
			System.out.println(e.getMessage());
			throw new CadastroException(EErroCadastro.ERRO_CADASTRO_CLIENTE);
		}
		return true;
	}

	public static boolean editar(Integer codigo) throws EdicaoException {

		try {
			Cliente clienteBanco = new ClienteDao().consultaCompleta(codigo);

			Integer opcao = 99;
			System.out.print("**** EDITOR DE CLIENTE ****");
			while (opcao != 0) {
				System.out.println("\n\tSELECIONE O ITEM QUE DESEJA EDITAR\t:");
				System.out.println("\n\t\t1----------NOME\t" + clienteBanco.getNome());
				System.out.println("\n\t\t2----------ENDEREÇO\t" + clienteBanco.getEndereco());
				System.out.println("\n\t\t3----------CONTATO\t" + clienteBanco.getContato().toString());
				System.out.println("\n\t\t0----------FINALIZAR");
				System.out.println("\n\n\t\t---:");
				opcao = Leitor.leInteger();
				switch (opcao) {
				case 1:
					clienteBanco.setNome(UtilCadastro.pedeNome());
					break;
				case 2:
					clienteBanco.setEndereco(UtilCadastro.pedeEndereco());
					break;
				case 3:
					ContatoController.editar(clienteBanco.getContatoid());
					break;
				case 0:
					try {
						new ClienteDao().altera(clienteBanco);

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
		} catch (DaoException | ConexaoException | EdicaoException | LeituraException e) {
			System.out.println(e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_CATEGORIA);
		}

	}
}
