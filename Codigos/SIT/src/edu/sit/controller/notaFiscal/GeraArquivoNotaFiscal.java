package edu.sit.controller.notaFiscal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.sit.erro.notaFiscal.EErroNotaFiscal;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.model.NotaFiscal;
import edu.sit.model.Produto;
import edu.sit.propriedades.Configuracao;
import edu.sit.uteis.Arquivo;

public class GeraArquivoNotaFiscal {
	
	public static boolean geraArquivo(NotaFiscal nota) throws NotaFiscalException {
		
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
		palavras.add("Valor Total--------------------------------------------------------------------------R$"+nota.getVenda().getValorCompra());
		palavras.add("-------------------------------------------------------------------------------------------");

		palavras.add("Data de emissão:\t"+nota.getDataEmissao().get(Calendar.DAY_OF_MONTH)+
				"/"+nota.getDataEmissao().get(Calendar.MONTH)+
				"/"+nota.getDataEmissao().get(Calendar.YEAR)+
				"\tHora:\t"+nota.getDataEmissao().get(Calendar.HOUR_OF_DAY)+":"+nota.getDataEmissao().get(Calendar.MINUTE));
		palavras.add("-------------------------------------------------------------------------------------------");
		
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
