package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.erro.cadastro.CadastroExeption;
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

	public static boolean cadastro() throws  CadastroExeption {

		String nome = null;
		ECargo cargo = null;
		String cpf = null;

		System.out.println("*****CADASTRO DE FUNCIONARIO*****");

		nome = UtilCadastro.pedeNome();
		cargo = pedeCargo();
		cpf = UtilCadastro.pedeCpf();
		
		if (ContatoController.cadastraContato()) {

			try {
				Funcionario funcionario = Funcionario.criaFuncionarioBanco(nome, cpf, cargo,
						new ContatoDao().pegaUltimoID());
				System.out.println(
						new FuncionarioDao().insere(funcionario) ? "Funcionario cadastrado com sucesso" : "Falha");
			} catch (DaoException | ConexaoException e) {
				System.out.println(e.getMessage());
				throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_FUNCIONARIO);
			}
		}

		return true;
	}

	public static boolean editar(Integer codigo) throws EdicaoException {
		
		try {
			Funcionario funcionarioBanco = new FuncionarioDao().consultaCompleta(codigo);

			System.out.print("*****EDITOR DE Funcionario*****");
			Integer opcao = 99;
			while (opcao != 0) {
				System.out.println("\n\tSELECIONE O ITEM QUE DESEJA EDITAR\t:");
				System.out.println("\n\t\t1----------CARGO\t" + funcionarioBanco.getCargo());
				System.out.println("\n\t\t2----------CONTATO\t" + funcionarioBanco.getContato());
				System.out.println("\n\t\t0----------FINALIZAR");
				System.out.println("\n\n\t\t---:");
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

					} catch (DaoException e) {
						System.out.println(e.getMessage());
						throw new EdicaoException(EErroEdicao.ERRO_EDICAO_FUNCIONARIO);
					}
					break;
				default:
					System.out.println("Valor Invalido\nSelecione uma das op��es oferecidas:");
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
				System.out.print("Cargo:\t");
				System.out.println(ECargo.GERENTE.ordinal() + "--Gerente");
				System.out.println(ECargo.VENDEDOR.ordinal() + "--Vendedors");
				cargo = ECargo.values()[Leitor.leInteger()];
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
		}

		return cargo;
	}

}