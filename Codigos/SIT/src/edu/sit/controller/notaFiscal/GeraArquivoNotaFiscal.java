package edu.sit.controller.notaFiscal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.sit.DataObject.ProdutoQuantidade;
import edu.sit.erro.notaFiscal.EErroNotaFiscal;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.model.NotaFiscal;
import edu.sit.propriedades.Configuracao;
import edu.sit.uteis.Arquivo;

public class GeraArquivoNotaFiscal {
	
	public static boolean geraArquivo(NotaFiscal nota) throws NotaFiscalException {
		
		List<String> palavras = new ArrayList<String>();
		palavras.add("");
		palavras.add("Cliente:");
		palavras.add(String.format("%-35s", "Nome: " + nota.getVenda().getCliente().getNome()) + 
					 String.format("%30s", "CPF: " + nota.getVenda().getCliente().getCpf()));
		palavras.add("");
		palavras.add("-----------------------------------------------------------------");
		palavras.add("");
		palavras.add("Funcionario:");
		palavras.add(String.format("%-35s", "Nome: " + nota.getVenda().getFuncionario().getNome()) +  
				     String.format("%30s", "Cargo: " + nota.getVenda().getFuncionario().getCargo().getDescricao()));
		palavras.add("");
		palavras.add("-----------------------------------------------------------------");
		palavras.add("");
		palavras.add("Produtos:\t\t\t\t\tQuantidade:");
		for (ProdutoQuantidade produto : nota.getVenda().getProdutos()) {
			palavras.add(produto.getItensPedido().getNome()+"\t\tR$"+produto.getItensPedido().getValorUnitario() + "\t\t"+produto.getQuantidadeProduto()+"\n");
			
		}
		palavras.add("");
		palavras.add("-----------------------------------------------------------------");
		palavras.add(String.format("%-40s", "Valor Total") + 
					 String.format("%25s", "R$" + nota.getVenda().getValorCompra()));
		palavras.add("-----------------------------------------------------------------");
		palavras.add("");
		palavras.add(String.format("%-40s", "\nData de emissão: " + nota.getDataEmissao().get(Calendar.DAY_OF_MONTH) +
								   "/" + (nota.getDataEmissao().get(Calendar.MONTH) +1)+
								   "/" + nota.getDataEmissao().get(Calendar.YEAR)) +
				     String.format("%26s", "Hora: " + nota.getDataEmissao().get(Calendar.HOUR_OF_DAY)+":" + 
								   nota.getDataEmissao().get(Calendar.MINUTE)));
		
		try {
			String path = Configuracao.getPropriedade("localNota")+"\\nota"+nota.getVenda().getId().toString()+".txt";
			Arquivo.gravaArquivo(path, palavras, false);
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			throw new NotaFiscalException(EErroNotaFiscal.ERRO_GRAVA_ARQUIVO);
		}
		return true;
	}

}
