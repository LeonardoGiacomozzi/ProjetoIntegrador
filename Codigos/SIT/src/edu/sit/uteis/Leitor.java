package edu.sit.uteis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.sit.erro.leitura.*;;

public class Leitor {

	public static String leString() throws LeituraEsception {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

		try {
			return teclado.readLine();
		} catch (IOException e) {
			throw new LeituraEsception(EErroLeitura.ERRO_LER_STRING);
		}
	}

	public static Integer leInteger() throws LeituraEsception {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

		try {
			return Integer.parseInt(teclado.readLine());
		} catch (IOException e) {
			throw new LeituraEsception(EErroLeitura.ERRO_LER_INTEGER);
		}

	}

	public static Double leDouble() throws LeituraEsception {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("a");

		try {
			return Double.parseDouble(teclado.readLine());
		} catch (IOException e) {
			throw new LeituraEsception(EErroLeitura.ERRO_LER_STRING);
		}

	}

	public static LocalDate leData() throws LeituraEsception {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		try {
			DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate ld = LocalDate.parse(teclado.readLine(), formatador);
			return ld;
		} catch (IOException e) {
			throw new LeituraEsception(EErroLeitura.ERRO_LER_DATA);
		}

	}

	public static String leCpf() throws LeituraEsception {
		Pattern padrao = Pattern.compile("[0-9].[0-9].[0-9]-[0-9]{3,3,3,2}");
		String cpf = leString();
		Matcher matcher = padrao.matcher(cpf);
		if (matcher.matches()) {
			return cpf;
		}
		throw new LeituraEsception(EErroLeitura.ERRO_LER_CPF);
	}
	
	public static String leCnpj() throws LeituraEsception {
		Pattern padrao = Pattern.compile("(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)");
		String cnpj = leString();
		Matcher matcher = padrao.matcher(cnpj);
		if (matcher.matches()) {
			return cnpj;
		}
		throw new LeituraEsception(EErroLeitura.ERRO_LER_CNPJ);
	}

}
