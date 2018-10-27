package edu.sit.view.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Leitura {
	public static String leitor() {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		String escolha = "";
		try {
			escolha = teclado.readLine();
		} catch (IOException e) {
			System.out.println("Erro");
		}
	}
}
