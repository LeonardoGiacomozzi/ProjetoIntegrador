package edu.sit.controller.relatorio;

import java.io.IOException;
import java.time.LocalDate;
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
			palavras.add("Funcionario "+venda.getFuncionario().getNome()
					+"\tCliente "+venda.getCliente().getNome()
					+"\tValor "+ venda.getValor()
					+"\tData da venda "+venda.getDataVenda());
		}
		String path=UtilCadastro.pedeNome("\nInforme onde deseja salvar o relatório: ")+"\\Relatorio"+LocalDate.now()+".txt";
		try {
			Arquivo.gravaArquivo(path, palavras, false);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw new NotaFiscalException(EErroNotaFiscal.ERRO_GRAVA_ARQUIVO);
		}
		System.out.println("\nRelatório gerado com SUCESSO!\n");
		return true;
	}
}
