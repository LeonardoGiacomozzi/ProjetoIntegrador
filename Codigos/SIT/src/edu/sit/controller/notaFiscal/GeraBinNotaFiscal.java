package edu.sit.controller.notaFiscal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import edu.sit.erro.notaFiscal.EErroNotaFiscal;
import edu.sit.erro.notaFiscal.NotaFiscalException;
import edu.sit.model.NotaFiscal;

public class GeraBinNotaFiscal {

	public static boolean geraBin(NotaFiscal nota) throws NotaFiscalException {
		
		ObjectOutputStream grava;
		String path = System.getProperty("user.dir")+"\\NotasBin\\nota"+nota.getId()+".bin";
		
		try {
			grava = new ObjectOutputStream(new FileOutputStream(path));
			grava.writeObject(nota.toString());
			grava.flush();
			grava.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw new NotaFiscalException(EErroNotaFiscal.ERRO_GRAVAR_BINARIO);
		}
		
		return true;
	}
}
