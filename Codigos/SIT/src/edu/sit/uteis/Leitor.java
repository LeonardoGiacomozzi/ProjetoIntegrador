package edu.sit.uteis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import edu.sit.erro.leitura.EErroLeitura;
import edu.sit.view.menu.LeituraException;

public class Leitor {

	public static String leString() throws LeituraException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			return  teclado.readLine();
		} catch (IOException e) {
			throw new LeituraException(EErroLeitura.ERRO_LER_STRING);
		}
	}
	
	public static Integer leInteger() throws LeituraException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			return  Integer.parseInt(teclado.readLine());
		} catch (IOException e) {
			throw new LeituraException(EErroLeitura.ERRO_LER_INTEGER);
		}
		
	}
	
	public static Double leDouble() throws LeituraException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("a");
		
		try {
			return  Double.parseDouble(teclado.readLine());
		} catch (IOException e) {
			throw new LeituraException(EErroLeitura.ERRO_LER_STRING);
		}
		
	}
	
	public static LocalDate leData() throws LeituraException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		try {
			DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate ld = LocalDate.parse(teclado.readLine(),formatador);
			return ld;
		} catch (IOException e) {
			throw new LeituraException(EErroLeitura.ERRO_LER_DATA);
		}
		
	}

}
