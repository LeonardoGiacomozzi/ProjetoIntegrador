package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.ECargo;
import edu.sit.model.Funcionario;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;

public class FuncionarioController {

	public static boolean cadastro() throws  CadastroException {

		String nome = null;
		ECargo cargo = null;
		String cpf = null;

		System.out.println("\n**** CADASTRO DE FUNCIÓNARIO ****");
		nome = UtilCadastro.pedeNome("Nome: \t");
		cargo = pedeCargo();
		cpf = UtilCadastro.pedeCpf();
		try {
			while(new FuncionarioDao().consultaPorCpf(cpf)!= 0) {
				System.out.println("CPF já Cadastrado");
				System.out.println("Informe um Novo CPF");
				cpf = UtilCadastro.pedeCpf();
			}
		} catch (ConexaoException | DaoException e1) {
			System.out.println(e1.getMessage());
		}
		
		if (ContatoController.cadastro()) {

			try {
				Funcionario funcionario = Funcionario.criaFuncionarioBanco(nome, cpf, cargo,
						new ContatoDao().pegaUltimoID());
				System.out.println(
						new FuncionarioDao().insere(funcionario) ? "\nFuncionário cadastrado com SUCESSO!\n\n" : "\nFalha\n");
			} catch (DaoException | ConexaoException e) {
				System.out.println(e.getMessage());
				throw new CadastroException(EErroCadastro.ERRO_CADASTRO_FUNCIONARIO);
			}
		}

		return true;
	}

	public static boolean editar(Integer codigo) throws EdicaoException {
		
		try {
			Funcionario funcionarioBanco = new FuncionarioDao().consultaCompleta(codigo);

			System.out.print("\n**** EDIÇÃO DE FUNCIONÁRIO ****\n");
			Integer opcao = Integer.MAX_VALUE;
			while (opcao != 0) {
				System.out.println("\nFuncionário [" + funcionarioBanco.getNome() + "]");
				System.out.println(String.format("%-21s", "\n1 - Cargo: ") + funcionarioBanco.getCargo());
				System.out.println(String.format("%-20s", "2 - Contato: ") + "Telefone: " + funcionarioBanco.getContato().getTelefone());
				System.out.println(String.format("%-20s", "") + "Email: " + funcionarioBanco.getContato().getEmail());
				System.out.println("\n0 - Finalizar");
				System.out.print("\nInforme a opção que deseja alterar: \t");
				opcao = Leitor.leInteger();

				switch (opcao) {
				case 1:
					funcionarioBanco.setCargo(pedeCargo());
					break;
				case 2:
					ContatoController.editar(funcionarioBanco.getContatoid());
					break;
				case 0:

					try {
						new FuncionarioDao().altera(funcionarioBanco);
						System.out.println("\nFuncionário alterado com SUCESSO!");
					} catch (DaoException e) {
						System.out.println(e.getMessage());
						throw new EdicaoException(EErroEdicao.ERRO_EDICAO_FUNCIONARIO);
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
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_FUNCIONARIO);
		}
		
	}

	private static ECargo pedeCargo() {

		ECargo cargo = null;

		while (cargo == null) {
			try {
				System.out.print("\nCargo:\t");
				System.out.println(ECargo.GERENTE.ordinal() + " - Gerente");
				System.out.print("\t" + ECargo.VENDEDOR.ordinal() + " - Vendedor\n\t");
				cargo = ECargo.values()[Leitor.leInteger()];
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}

		return cargo;
	}
}