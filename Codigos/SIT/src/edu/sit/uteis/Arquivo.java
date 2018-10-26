package edu.sit.uteis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Arquivo {
	
	public static void gravaArquivo(String nomeArq, Collection<String> linhas, boolean fimArq) throws IOException {
		PrintWriter arquivo = new PrintWriter(new FileWriter(new File(nomeArq), fimArq), true);
		Iterator<String> dados = linhas.iterator();
		while (dados.hasNext()) {
			arquivo.println(dados.next());
		}
		arquivo.close();
	}

	public static void gravaArquivo(String nomeArq, String linha, boolean fimArq) throws IOException {
		PrintWriter arquivo = new PrintWriter(new FileWriter(new File(nomeArq), fimArq), true);
		arquivo.println(linha);
		arquivo.close();
	}

	public static Collection<String> leArquivo(String nomeArq) throws IOException {
		BufferedReader arquivo = new BufferedReader(new FileReader(new File(nomeArq)));
		Collection<String> linhas = new ArrayList<String>();
		String linha = null;
		while ((linha = arquivo.readLine()) != null) {
			linhas.add(linha);
		}
		arquivo.close();
		return linhas;
	}

	public static String leArquivo(String nomeArq, boolean todasLinhas, String separador) throws IOException {
		separador = separador instanceof String ? separador : "#";
		BufferedReader arquivo = new BufferedReader(new FileReader(new File(nomeArq)));
		StringBuffer agrupaTodas = new StringBuffer("");
		String linha = "";
		if (todasLinhas) {
			while ((linha = arquivo.readLine()) != null) {
				agrupaTodas.append(linha + separador);
			}
		}
		arquivo.close();
		return todasLinhas ? agrupaTodas.toString() : arquivo.readLine();
	}

}
