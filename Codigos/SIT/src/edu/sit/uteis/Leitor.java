package edu.sit.uteis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		
		try {
			return  Double.parseDouble(teclado.readLine());
		} catch (IOException e) {
			throw new LeituraException(EErroLeitura.ERRO_LER_STRING);
		}
		
	}
	
	public static Date leData() throws LeituraException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return new Date(sdf.format(teclado.readLine()));
		} catch (IOException e) {
			throw new LeituraException(EErroLeitura.ERRO_LER_STRING);
		}
		
	}

}
