package edu.sit.controller.cadastro;


import java.time.LocalDate;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Cliente;
import edu.sit.uteis.cadastro.UtilCadastro;

public class ClienteController {

	public static boolean cadastro() throws CadastroExeption {

		String nome = null;
		String cpf = null;
		String endereco = null;
		LocalDate dataNascimento = null;
		System.out.println("*****CADASTRO DE CLIENTE*****");

		nome = UtilCadastro.pedeNome();
		cpf = UtilCadastro.pedeCpf();
		endereco = UtilCadastro.pedeEndereco();
		dataNascimento = UtilCadastro.pedeDataNascimento();
		
		try {
			if (ContatoController.cadastraContato()) {

				try {
					Cliente cliente = Cliente.criaClienteBanco(nome, dataNascimento, endereco, cpf, new ContatoDao().pegaUltimoID());
					System.out.println(new ClienteDao().insere(cliente) ? "Cliente cadastrado com sucesso" : "Falha");
				} catch (DaoException e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (ConexaoException e) {
			System.out.println(e.getMessage());
			throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_CLIENTE);
		}
		return true;
	}

	public static boolean editar(Integer codigo) throws EdicaoException {
		
		try {
			Cliente clienteBanco = new ClienteDao().consulta(codigo);
			
			System.out.print("*****EDITOR DE CLIENTE*****");
			clienteBanco.setNome(UtilCadastro.pedeNome());
			clienteBanco.setContato(ContatoController.editar(clienteBanco.getContatoid()));
			clienteBanco.setCpf(UtilCadastro.pedeCpf());
			clienteBanco.setDataDeNascimento(UtilCadastro.pedeDataNascimento());
			clienteBanco.setEndereco(UtilCadastro.pedeEndereco());;
			try {
				new ClienteDao().altera(clienteBanco);
				
			} catch (DaoException e) {
				System.out.println(e.getMessage());
				throw new EdicaoException(EErroEdicao.ERRO_EDICAO_CATEGORIA);
			}
			return true;
		} catch (DaoException | ConexaoException | EdicaoException e) {
			System.out.println(e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_CATEGORIA);
		}
		
	}
}
