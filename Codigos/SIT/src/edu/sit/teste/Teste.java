package edu.sit.teste;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.VendaDao;
import edu.sit.controller.cadastro.UsuarioController;
import edu.sit.controller.notaFiscal.GeraArquivoNotaFiscal;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.NotaFiscal;
import edu.sit.model.Usuario;
import edu.sit.uteis.CalculoIdade;

public class Teste {

	@Test
	public void testaIdade() {

		assertEquals(18,
				CalculoIdade.calculaIdade(LocalDate.parse("02/03/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
	}

	@Test
	public void testaValidaLogin() {

		assertEquals(true, UsuarioController.validaLogin(Usuario.criaUsuario("admim", "@ADM")));

	}

	@Test
	public void testaGeraNotaFiscal() throws DaoException, ConexaoException, NotaFiscalException {

		assertEquals(true,
				GeraArquivoNotaFiscal.geraArquivo(NotaFiscal.criaNotaFiscal(new VendaDao().consultaCompleta(1))));
	}

	@Test
	public void testaValidaLoginException() {

		assertEquals(false, UsuarioController.validaLogin(Usuario.criaUsuario("eu", "32")));

	}
	

}
