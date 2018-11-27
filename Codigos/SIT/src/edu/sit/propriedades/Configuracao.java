package edu.sit.propriedades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import edu.sit.erro.leitura.LeituraException;
import edu.sit.propriedades.dao.EErroPropriedade;
import edu.sit.propriedades.dao.GravaArquivoException;
import edu.sit.uteis.Leitor;

public class Configuracao {
	
	
	public boolean gravaArquivo() throws GravaArquivoException {
		
		String caminho = System.getProperty("user.dir") + ".\\propertiers\\dados.propertiers";
		
		FileInputStream arquivoEntrada;
		try {
			arquivoEntrada = new FileInputStream(new File(caminho));
		} catch (FileNotFoundException e1) {
			throw new GravaArquivoException(EErroPropriedade.ERRO_ABRIR_ARQUIVO);
		}
		
		Properties propriedades = new Properties();
		
		try {
			propriedades.load(arquivoEntrada);
		} catch (IOException e1) {
			throw new GravaArquivoException(EErroPropriedade.ERRO_CARREGAR_ARQUIVO);
		}
		
		try {
			arquivoEntrada.close();
		} catch (IOException e1) {
			throw new GravaArquivoException(EErroPropriedade.ERRO_FECHAR_ARQUIVO);
		}
		
		
		try {
		
		System.out.print("Usuario: ");
		propriedades.setProperty("Usuario", Leitor.leString());
		System.out.print("Senha: ");
		propriedades.setProperty("Senha", Leitor.leString());
		System.out.print("Nome Banco :");
		propriedades.setProperty("Banco", Leitor.leString());
		System.out.print("Local:");
		propriedades.setProperty("Local", Leitor.leString());
		
			
		}catch(LeituraException e) {
			System.out.println(e.getMessage());
		}
		
		try {
		FileOutputStream arquivoSaida = new FileOutputStream(caminho);
		propriedades.store(arquivoSaida, "----------");
		arquivoSaida.close();
		return true;
		}catch(IOException e) {
			throw new GravaArquivoException(EErroPropriedade.ERRO_Grava_Arquivo);
		}

	}
}