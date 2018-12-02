package edu.sit.controller.relatorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.model.Venda;
import edu.sit.uteis.Arquivo;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;

public class RelatorioVenda {

	public static boolean geraArquivo(ArrayList<Venda> vendas) throws NotaFiscalException {
		Integer aux = Integer.MAX_VALUE;
		List<String> palavras = new ArrayList<String>();
		palavras.add("");
		for (Venda venda : vendas) {
			palavras.add("");
			palavras.add(String.format("%-35s", "Funcionário: " + venda.getFuncionario().getNome())
					+ String.format("%30s", "Data da Venda: " + venda.getDataVenda()));
			palavras.add(String.format("%-35s", "Cliente: " + venda.getCliente().getNome())
					+ String.format("%30s", "Valor: R$" + venda.getValor()));
			palavras.add("");
			palavras.add("-----------------------------------------------------------------");
		}

		String nomeArq = UtilCadastro.pedeNome("\nInforme o nome do relatório: \t");
		while (aux != 0) {
			String path = UtilCadastro.pedeNome("Informe onde deseja salvar o relatório: \t") + "\\" + nomeArq + ".txt";

			try {
				Arquivo.gravaArquivo(path, palavras, false);
				System.out.println("\nRelatório gerado com SUCESSO!\n");
				aux = 0;
				return true;
			} catch (IOException e) {
				System.out.println("\nNome do caminho incorrento!");
			} finally {
				if (aux != 0) {
					System.out.println("\nAperte [1] para Tentar novamente");
					System.out.println("Aperta [0] para Voltar");
					try {
						aux = Leitor.leInteger();
						System.out.print("\n");
					} catch (Exception e2) {
						System.out.println(e2.getMessage());
					} 
				}
			}
		}
		return (aux == 0);
	}
}
