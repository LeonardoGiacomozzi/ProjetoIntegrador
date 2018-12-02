package edu.sit.uteis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.sit.erro.leitura.*;;

public class Leitor {

	public static String leString() throws LeituraException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

		try {
			return teclado.readLine();
		} catch (IOException e) {
			throw new LeituraException(EErroLeitura.ERRO_LER_STRING);
		}
	}

	public static Integer leInteger() throws LeituraException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		try {
			return Integer.parseInt(teclado.readLine());
		} catch (IOException | NumberFormatException e) {
			throw new LeituraException(EErroLeitura.ERRO_LER_INTEGER);
		}

	}

	public static Double leDouble() throws LeituraException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		try {
			return Double.parseDouble(teclado.readLine());
		} catch (IOException | NumberFormatException e) {
			throw new LeituraException(EErroLeitura.ERRO_LER_DOUBLE);
		}

	}

	public static LocalDate leData() throws LeituraException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		try {
			DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate ld = LocalDate.parse(teclado.readLine(), formatador);
			return ld;
		} catch (IOException | DateTimeParseException e) {
			throw new LeituraException(EErroLeitura.ERRO_LER_DATA);
		}

	}

	public static String leCpf() throws LeituraException {
		Pattern padrao = Pattern.compile("(^\\d{3}.\\d{3}.\\d{3}-\\d{2}$)");
		String cpf = leString();
		Matcher matcher = padrao.matcher(cpf);
		if (matcher.matches()) {
			return cpf;
		}
		throw new LeituraException(EErroLeitura.ERRO_LER_CPF);
	}
	
	public static String leCnpj() throws LeituraException {
		Pattern padrao = Pattern.compile("(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)");
		String cnpj = leString();
		Matcher matcher = padrao.matcher(cnpj);
		if (matcher.matches()) {
			return cnpj;
		}
		throw new LeituraException(EErroLeitura.ERRO_LER_CNPJ);
	}
	
	public static String leFone() throws LeituraException {
		Pattern padrao = Pattern.compile("(^\\(\\d{2}\\)\\d{4,5}-\\d{4}$)");
		Pattern padrao2 = Pattern.compile("SemFone");
		String fone = leString();
		Matcher matcher = padrao.matcher(fone);
		Matcher matcher2 = padrao2.matcher(fone);
		if (matcher.matches() | matcher2.matches()) {
			return fone;
		}
		throw new LeituraException(EErroLeitura.ERRO_LER_FONE);
	}
	
	public static String leEmail() throws LeituraException {
		Pattern padrao = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`"
				+ "{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
		Pattern padrao2 = Pattern.compile("SemEmail");
		String fone = leString();
		Matcher matcher = padrao.matcher(fone);
		Matcher matcher2 = padrao2.matcher(fone);
		if (matcher.matches() | matcher2.matches()) {
			return fone;
		}
		throw new LeituraException(EErroLeitura.ERRO_LER_EMAIL);
	}
}
