package edu.sit.uteis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Leitor {

	public static String leitor() {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = teclado.readLine();
		} catch (IOException e) {
			System.out.println("Erro");
		}
		return input;
	}
	
}
