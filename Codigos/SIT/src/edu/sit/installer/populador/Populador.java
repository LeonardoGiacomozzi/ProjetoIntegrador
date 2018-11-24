package edu.sit.installer.populador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ClienteDao;
import edu.sit.bancodedados.dao.ContatoDao;
import edu.sit.bancodedados.dao.FuncionarioDao;
import edu.sit.erro.instalacao.EErroInstalacao;
import edu.sit.erro.instalacao.InstalacaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Cliente;
import edu.sit.model.Contato;
import edu.sit.model.ECargo;
import edu.sit.model.Funcionario;
import edu.sit.uteis.Arquivo;

public class Populador {
	
	public static boolean cliente () throws InstalacaoException {
		
		try {
		List<String> clientesTxt = (List<String>) Arquivo.leArquivo(System.getProperty("user.dir") + "/populador/clientes.txt");
		for (String cliente : clientesTxt) {
			String[] dados = cliente.split(";");
				new ContatoDao().insere(Contato.criaContato(dados[4],dados[5]));
				new ClienteDao().insere(Cliente.criaClienteBanco(dados[0], LocalDate.parse(dados[1], DateTimeFormatter.ofPattern(
						"dd/MM/yyyy")), dados[2], dados[3], new ContatoDao().pegaUltimoID()));
		}
		return true;
		} catch (DaoException | ConexaoException | IOException e) {
			System.out.println(e.getMessage());
			throw new InstalacaoException(EErroInstalacao.ERRO_POPULAR_CLIENTES);
		}
		
	}
	
	
	public static boolean funcionario () throws InstalacaoException {
		
		try {
		List<String> funcionarioTxt = (List<String>) Arquivo.leArquivo(System.getProperty("user.dir") + "/populador/funcionario.txt");
		for (String funcionario : funcionarioTxt) {
			String[] dados = funcionario.split(";");
				new ContatoDao().insere(Contato.criaContato(dados[3],dados[4]));
				new FuncionarioDao().insere(Funcionario.criaFuncionario(dados[0], dados[1],ECargo.valueOf(dados[2]),new ContatoDao().pegaUltimoID()));
		}
		return true;
		} catch (DaoException | ConexaoException | IOException e) {
			System.out.println(e.getMessage());
			throw new InstalacaoException(EErroInstalacao.ERRO_POPULAR_FUNCIONARIO);
		}
		
	}

}
