package edu.sit.controller.cadastro;

import java.time.LocalDate;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.erro.cadastro.CadastroException;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.controller.ControllerException;
import edu.sit.erro.controller.EErroController;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Cliente;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;
import edu.sit.view.controllers.ClienteView;

public class ClienteController {

	public static boolean cadastro() throws CadastroException {

		String nome = null;
		String cpf = null;
		String endereco = null;
		LocalDate dataNascimento = null;

		nome = UtilCadastro.pedeNome("Nome: \t");
		cpf = UtilCadastro.pedeCpf();
		try {
			while(new ClienteDao().consultaCPF(cpf) != null) {
				System.out.println("\nCPF j� Cadastrado! Informe um novo CPF...\n" );
				cpf = UtilCadastro.pedeCpf();
			}
				
		} catch (DaoException | ConexaoException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		endereco = UtilCadastro.pedeEndereco();
		dataNascimento = UtilCadastro.pedeDataNascimento();
		if(Cliente.getIdade(dataNascimento)<18) {
			System.out.println("\nN�o foi poss�vel cadastrar o cliente!\nCliente menor de IDADE!");
			return false;
			
		}

		try {
			if (ContatoController.cadastro()) {

				try {
					Cliente cliente = Cliente.criaClienteBanco(nome, dataNascimento, endereco, cpf,
							new ContatoDao().pegaUltimoID());
					System.out.println(
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
		System.out.print("\n**** EDI��O DE CLIENTE ****\n");
		while (opcao != 0) {
			System.out.println("\n\t\t1----------NOME\t" + clienteBanco.getNome());
			System.out.println("\n\t\t2----------ENDERE�O\t" + clienteBanco.getEndereco());
			System.out.println("\n\t\t3----------CONTATO\t" + clienteBanco.getContato().toString());
			System.out.println("\n\t\t0----------FINALIZAR");
			System.out.println("\n\n\t\t---:");
			try {
				opcao = Leitor.leInteger();
			} catch (LeituraException e1) {
				System.out.println(e1.getMessage());
			}
			switch (opcao) {
			case 1:
				clienteBanco.setNome(UtilCadastro.pedeNome("Informe o novo Nome: \t"));
				break;
			case 2:
				clienteBanco.setEndereco(UtilCadastro.pedeEndereco());
				break;
			case 3:
				ContatoController.editar(clienteBanco.getContatoid());
				break;
			case 0:
			
				try {
					System.out.println(new ClienteDao().altera(clienteBanco) ? "\nCliente alterado com SUCESSO!" : "\nFalha");
				} catch (DaoException | ConexaoException e) {
					System.out.println(e.getMessage());
				}

				
				break;
			default:
				System.out.println("\nValor Inv�lido!\n\nSelecione uma das op��es oferecidas: \t");
				break;
			}
			}
		}catch (EdicaoException  | DaoException e ) {
				System.out.println(e.getMessage());
				throw new EdicaoException(EErroEdicao.ERRO_BUSCA_CATEGORIA);
			} catch (ConexaoException e2) {
			System.out.println(e2.getMessage());
		}
		
		
		return true;
		
		

	}
	
	public static boolean buscaPorCPF() throws ControllerException {
		System.out.println("Informe o CPF do cliente para busca :");
		String cpf = UtilCadastro.pedeCpf();
		try {
			Cliente clientebanco = new ClienteDao().consultaCPF(cpf);
			ClienteView.exibeCliente(clientebanco);
			return true;
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw  new ControllerException(EErroController.ERRO_BUSCAR_CLIENTE_CPF);
		}
	}

	

}
