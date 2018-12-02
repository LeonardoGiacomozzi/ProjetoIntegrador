package edu.sit.controller.relatorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.sit.erro.notaFiscal.EErroNotaFiscal;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.model.Venda;
import edu.sit.uteis.Arquivo;
import edu.sit.uteis.cadastro.UtilCadastro;

public class RelatorioVenda {

	public static boolean geraArquivo(ArrayList<Venda> vendas) throws NotaFiscalException {

		List<String> palavras = new ArrayList<String>();
		palavras.add("");
		for (Venda venda : vendas) {
			palavras.add("");
			palavras.add(String.format("%-35s", "Funcionário: " + venda.getFuncionario().getNome()) +
					     String.format("%30s", "Data da Venda: " + venda.getDataVenda()));
			palavras.add(String.format("%-35s", "Cliente: " + venda.getCliente().getNome()) +
						 String.format("%30s", "Valor: R$" + venda.getValor()));     
			palavras.add("");
			palavras.add("-----------------------------------------------------------------");
		}
		
		String nomeArq = UtilCadastro.pedeNome("\nInforme o nome do relatório: \t");
		String path = UtilCadastro.pedeNome("Informe onde deseja salvar o relatório: \t") + "\\" + nomeArq + ".txt";

		try {
			Arquivo.gravaArquivo(path, palavras, false);
		} catch (IOException e) {
			throw new NotaFiscalException(EErroNotaFiscal.ERRO_GRAVA_ARQUIVO);
		}
		System.out.println("\nRelatório gerado com SUCESSO!\n");
		return true;

	}
}
