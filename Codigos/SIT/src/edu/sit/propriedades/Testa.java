package edu.sit.propriedades;

import java.io.IOException;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.NotaFiscalDao;
import edu.sit.bancodedados.dao.VendaDao;
import edu.sit.controller.notaFiscal.GeraArquivoNotaFiscal;
import edu.sit.controller.notaFiscal.GeraBinNotaFiscal;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.erro.propriedades.PropriedadesException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.NotaFiscal;
import edu.sit.uteis.Arquivo;

public class Testa {
	public static void main(String[] args) throws IOException {
//		try {
//			Configuracao.setPropriedades();
//		} catch (PropriedadesException e) {
//			System.out.println("ERRO");
//		}
		System.out.println(Configuracao.getPropriedade("banco"));
		System.out.println(Configuracao.getPropriedade("usuario"));
		System.out.println(Configuracao.getPropriedade("senha"));
		System.out.println(Configuracao.getPropriedade("localNota"));
		
		
		try {
			GeraArquivoNotaFiscal.geraArquivo(NotaFiscal.criaNotaFiscal( new VendaDao().consultaCompleta(1)));
			GeraBinNotaFiscal.geraBin(NotaFiscal.criaNotaFiscal( new VendaDao().consultaCompleta(1)));
		} catch (NotaFiscalException | DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		new NotaFiscalDao().insere(Arquivo.leArquivo(nomeArq))
		
	}
	}
