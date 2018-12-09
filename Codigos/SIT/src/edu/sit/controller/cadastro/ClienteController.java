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

		nome = UtilCadastro.pedeNome("Nome: \t");
		cpf = UtilCadastro.pedeCpf();
		try {
			while (new ClienteDao().consultaCPF(cpf) != null) {
				System.out.println("\nCPF já Cadastrado! Informe um novo CPF...\n");
				cpf = UtilCadastro.pedeCpf();
			}

		} catch (DaoException | ConexaoException e1) {
			System.out.println(e1.getMessage());
		}
		endereco = UtilCadastro.pedeEndereco();
		dataNascimento = UtilCadastro.pedeDataNascimento();
		if (Cliente.getIdade(dataNascimento) < 18) {
			System.out.println("\nNão foi possível cadastrar o cliente!\nCliente menor de IDADE!");
			return false;

		}

		try {
			if (ContatoController.cadastro()) {

				try {
					Cliente cliente = Cliente.criaClienteBanco(nome, dataNascimento, endereco, cpf,
							new ContatoDao().pegaUltimoID());
					System.out.print(
							new ClienteDao().insere(cliente) ? "\nCliente cadastrado com SUCESSO!" : "\nFalha\n");
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
			System.out.print("\n**** EDIÇÃO DE CLIENTE ****\n");
			while (opcao != 0) {
				
				System.out.println(String.format("%-27s", "\n1 - Nome: ") + clienteBanco.getNome());
				System.out.println(String.format("%-26s", "2 - Endereço: ") + clienteBanco.getEndereco());
				System.out.println(String.format("%-26s", "3 - Contato: ") + "Telefone: "
						+ clienteBanco.getContato().getTelefone());
				System.out.println(String.format("%-26s", "") + "Email: " + clienteBanco.getContato().getEmail());
				System.out.println("\n0 - Finalizar");
				System.out.print("\nInforme a opção que deseja alterar: \t");
				try {
					opcao = Leitor.leInteger();
				} catch (LeituraException e1) {

				}
				switch (opcao) {
				case 1:
					clienteBanco.setNome(UtilCadastro.pedeNome("\nInforme o novo Nome: \t"));
					
					break;
				case 2:
					clienteBanco.setEndereco(UtilCadastro.pedeEndereco());
					break;
				case 3:
					ContatoController.editar(clienteBanco.getContatoid());
					System.out.print(new ClienteDao().altera(clienteBanco)?"":"");
					clienteBanco = new ClienteDao().consultaCompleta(codigo);
					break;
				case 0:

					try {
						System.out.print(new ClienteDao().altera(clienteBanco) ? "\nCliente alterado com SUCESSO!\n\n"
								: "\nFalha");
					} catch (DaoException | ConexaoException e) {
						System.out.println(e.getMessage());
					}

					break;
				default:
					System.out.println("\nValor Inválido!\n\nSelecione uma das opções oferecidas: \t");
					break;
				}
			}

		} catch (EdicaoException | DaoException e) {
			System.out.println(e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_CATEGORIA);
		} catch (ConexaoException e2) {
			System.out.println(e2.getMessage());
		}
		return true;
	}
}
