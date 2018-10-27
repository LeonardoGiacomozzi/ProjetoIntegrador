package edu.sit.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import edu.sit.erro.notaFiscal.EErroNotaFiscal;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.model.NotaFiscal;

public class GeraBinNotaFiscal {

	public static boolean geraBin(NotaFiscal nota,String caminho) throws NotaFiscalException {
		
		ObjectOutputStream grava;
		
		try {
			grava = new ObjectOutputStream(new FileOutputStream(caminho));
			grava.writeObject(nota.toString());
			grava.flush();
			grava.close();
		} catch (IOException e) {
			throw new NotaFiscalException(EErroNotaFiscal.ERRO_GRAVAR_BINARIO);
		}
		
		return true;
	}
}
