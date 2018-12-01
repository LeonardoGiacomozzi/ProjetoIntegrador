package edu.sit.controller.cadastro;

import edu.sit.erro.propriedades.PropriedadesException;
import edu.sit.propriedades.Configuracao;

public class ConfiguracaoController {
	
	public static boolean editar(String[] dados) throws PropriedadesException {
		
		String [] props = new String[4];
		props[0] = "banco;" + Configuracao.getPropriedade("banco");
		props[1] = (dados[0] instanceof String?dados[0]:Configuracao.getPropriedade("usuario"));
		props[2] = (dados[1] instanceof String?dados[1]:Configuracao.getPropriedade("senha"));
		props[3] = (dados[2] instanceof String?dados[2]:Configuracao.getPropriedade("localNota"));
		
		Configuracao.setPropriedades(props);
		return true;
	}
}
