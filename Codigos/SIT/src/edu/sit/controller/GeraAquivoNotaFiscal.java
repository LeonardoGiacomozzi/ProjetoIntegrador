package edu.sit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.sit.erro.notaFiscal.EErroNotaFiscal;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.model.NotaFiscal;
import edu.sit.model.Produto;
import edu.sit.uteis.Arquivo;

public class GeraAquivoNotaFiscal {
	
	public static boolean geraArquivo(NotaFiscal nota,String local) throws NotaFiscalException {
		
		List<String> palavras = new ArrayList<String>();
		palavras.add("Cliente:");
		palavras.add("Nome:\t"+nota.getCliente().getNome()+"\tCPF:\t"+nota.getCliente().getCpf());
		palavras.add("Funcionario:");
		palavras.add("Nome:\t"+nota.getFuncionario().getNome()+"\tCargo:\t"+nota.getFuncionario().getCargo().getDescricao());
		palavras.add("Produtos:");
		for (Produto produto : nota.getProdutos()) {
			palavras.add(produto.getNome()+"\t\tR$"+produto.getValorUnitario());
			
		}
		palavras.add("Data de emissão:\t"+nota.getDataEmissao().get(Calendar.DAY_OF_MONTH)+
				"/"+nota.getDataEmissao().get(Calendar.MONTH)+
				"/"+nota.getDataEmissao().get(Calendar.YEAR)+
				"\tHora:\t"+nota.getDataEmissao().get(Calendar.HOUR_OF_DAY)+":"+nota.getDataEmissao().get(Calendar.MINUTE));
		try {
			Arquivo.gravaArquivo(local, palavras, false);
		} catch (IOException e) {
			
			throw new NotaFiscalException(EErroNotaFiscal.ERRO_GRAVA_ARQUIVO);
			
		}
		return true;
	}

}
