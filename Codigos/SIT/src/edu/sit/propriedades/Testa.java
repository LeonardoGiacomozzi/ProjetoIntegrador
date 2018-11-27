package edu.sit.propriedades;

import java.io.IOException;

import edu.sit.erro.propriedades.PropriedadesException;

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
		
	}
	}
