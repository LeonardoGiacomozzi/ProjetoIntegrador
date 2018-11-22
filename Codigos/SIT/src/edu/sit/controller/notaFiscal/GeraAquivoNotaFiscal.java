package edu.sit.controller.notaFiscal;

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
		palavras.add("-------------------------------------------------------------------------------------------");
		palavras.add("Cliente:");
		palavras.add("Nome:\t"+nota.getVenda().getCliente().getNome()+"\tCPF:\t"+nota.getVenda().getCliente().getCpf());
		palavras.add("-------------------------------------------------------------------------------------------");
		palavras.add("Funcionario:");
		palavras.add("Nome:\t"+nota.getVenda().getFuncionario().getNome()+"\tCargo:\t"+nota.getVenda().getFuncionario().getCargo().getDescricao());
		palavras.add("-------------------------------------------------------------------------------------------");
		palavras.add("Produtos:");
		for (Produto produto : nota.getVenda().getProdutos()) {
			palavras.add(produto.getNome()+"\t\tR$"+produto.getValorUnitario());
			
		}
		palavras.add("-------------------------------------------------------------------------------------------");
		palavras.add("Valor Tota--------------------------------------------------------------------------R$"+nota.getTotal());
		palavras.add("-------------------------------------------------------------------------------------------");
		palavras.add("Data de emiss�o:\t"+nota.getDataEmissao().get(Calendar.DAY_OF_MONTH)+
				"/"+nota.getDataEmissao().get(Calendar.MONTH)+
				"/"+nota.getDataEmissao().get(Calendar.YEAR)+
				"\tHora:\t"+nota.getDataEmissao().get(Calendar.HOUR_OF_DAY)+":"+nota.getDataEmissao().get(Calendar.MINUTE));
		palavras.add("-------------------------------------------------------------------------------------------");
		
		try {
			Arquivo.gravaArquivo(local, palavras, false);
		} catch (IOException e) {
			
			throw new NotaFiscalException(EErroNotaFiscal.ERRO_GRAVA_ARQUIVO);
			
		}
		return true;
	}

}
