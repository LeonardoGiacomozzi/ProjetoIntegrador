package edu.sit.propriedades;

import java.io.IOException;

import edu.sit.propriedades.dao.GravaArquivoException;

public class Testa {
	public static void main(String[] args) throws IOException {
		Configuracao arquivo2 = new Configuracao();
		try {
			arquivo2.gravaArquivo();
		} catch (GravaArquivoException e) {
			System.out.println("ERRO");
		}
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("Usuario"));
		System.out.println(System.getProperty("Senha"));
		System.out.println(System.getProperty("Banco"));
		
	}
	}
