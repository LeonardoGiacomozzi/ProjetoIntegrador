package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.FornecedorDao;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.controller.ControllerException;
import edu.sit.erro.controller.EErroController;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Fornecedor;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;
import edu.sit.view.controllers.FornecedorView;

public class FornecedorController {
	
	public static boolean cadastro() throws CadastroException {
		String nome = null;
		String cnpj = null;
		String pessoaResponsavel = null;
		System.out.println("\n**** CADASTRO DE FORNECEDOR ****");

		nome = UtilCadastro.pedeNome("Nome: \t");
		cnpj = UtilCadastro.pedeCnpj();
		try {
			while(new FornecedorDao().consultaCNPJ(cnpj) != null) {
				System.out.println("\nCNPJ já Cadastrado! Informe um novo CNPJ...\n" );
				cnpj = UtilCadastro.pedeCnpj();
			}
		} catch (DaoException | ConexaoException e1) {
			System.out.println(e1.getMessage());
		}
		pessoaResponsavel = UtilCadastro.pedePessoaResponsavel();
		
		if (ContatoController.cadastro()) {

			try {
				Fornecedor fornecedor = new Fornecedor(nome, cnpj, pessoaResponsavel,
						new ContatoDao().pegaUltimoID());
				System.out.println(
						new FornecedorDao().insere(fornecedor) ? "\nFornecedor cadastrado com SUCESSO!\n\n" : "\nFalha\n");
			} catch (DaoException | ConexaoException e) {
				System.out.println(e.getMessage());
				throw new CadastroException(EErroCadastro.ERRO_CADASTRO_FORNECEDOR);
			}
		}
		return true;
	}
	
	public static boolean editar(Integer codigo) throws EdicaoException {
		try {
			Fornecedor fornecedorBanco = new FornecedorDao().consultaCompleta(codigo);
			System.out.print("\n**** EDIÇÃO DE FORNECEDOR ****\n");
			Integer opcao = 99;
			while (opcao != 0) {
				
				System.out.println(String.format("%-27s", "\n1 - Nome: ") + fornecedorBanco.getNome());
				System.out.println(String.format("%-26s", "2 - Pessoa Responsável: ") + fornecedorBanco.getPessoaResponsavel());
				System.out.println(String.format("%-26s", "3 - Contato: ") + "Telefone: " + fornecedorBanco.getContato().getTelefone());
				System.out.println(String.format("%-26s", "") + "Email: " + fornecedorBanco.getContato().getEmail());
				System.out.println("\n0 - Finalizar");
				System.out.print("\nInforme a opção que deseja alterar: \t");
				try {
					opcao = Leitor.leInteger();
				} catch (LeituraException e1) {
					System.out.println(e1.getMessage());
				}

				switch (opcao) {
				case 1:
					fornecedorBanco.setNome(UtilCadastro.pedeNome("\nInforme o novo Nome: \t"));
					break;
				case 2:
					System.out.print("\n");
					fornecedorBanco.setPessoaResponsavel(UtilCadastro.pedePessoaResponsavel());
					break;
				case 3:
					ContatoController.editar(fornecedorBanco.getContatoid());
					System.out.print(new FornecedorDao().altera(fornecedorBanco)?"":"");
					fornecedorBanco = new FornecedorDao().consultaCompleta(codigo);
					break;

				case 0:

					try {
						System.out.println(new FornecedorDao().altera(fornecedorBanco) ? "\nFornecedor alterado com SUCESSO!" : "\nFalha");
					} catch (DaoException e) {
						System.out.println(e.getMessage());
						throw new EdicaoException(EErroEdicao.ERRO_EDICAO_FORNECEDOR);
					}
					break;
				default:
					System.out.println("\nValor Inválido!\n\nSelecione uma das opções oferecidas: \t");
					break;

				}

			}
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_CATEGORIA);
		}
		return true;

	}

	public static boolean buscaPorCPF() throws ControllerException {
		System.out.println("Informe o CNPJ do Fornecedor para busca :");
		String cnpj = UtilCadastro.pedeCnpj();
		try {
			Fornecedor fornecedorbanco = new FornecedorDao().consultaCNPJ(cnpj);
			FornecedorView.exibeFornecedor(fornecedorbanco);
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw  new ControllerException(EErroController.ERRO_BUSCAR_CNPJ_FORNECEDOR);
		}
	}
}
