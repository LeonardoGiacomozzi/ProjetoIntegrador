package edu.sit.propriedades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.sit.erro.propriedades.EErroPropriedade;
import edu.sit.erro.propriedades.PropriedadesException;
import edu.sit.uteis.Arquivo;
import edu.sit.uteis.cadastro.UtilCadastro;

public class Configuracao {
	
	private static String path =System.getProperty("user.dir")+"\\propriedades\\propriedades.txt";
	
	public static boolean setPropriedades() throws PropriedadesException {

		String banco = "banco;" + UtilCadastro.pedeNome("\nInforme o nome que você deseja para o banco de dados: \n");
		String usuario = "usuario;" + UtilCadastro.pedeNome("\nInforme o nome do usuário do seu banco de dados: \n");
		String senha = "senha;" + UtilCadastro.pedeNome("\nInforme a senha de acesso: \n");
		String localNotas = "localNota;" + UtilCadastro.pedeNome("\nInforme o local onde deseja salvar as notas fiscais: \n");

		ArrayList<String> props = new ArrayList<>();
		props.add(banco);
		props.add(usuario);
		props.add(senha);
		props.add(localNotas);

		try {
			Arquivo.gravaArquivo(path, props, false);
		} catch (IOException e) {
			throw new PropriedadesException(EErroPropriedade.ERRO_SALVAR_PROPRIEDADES);
		}
		return true;
	}

	public static String getPropriedade(String nome ) {
		
		 Map<String,String> itensPropriedade = new HashMap<String,String>();
			List<String> propriedades = new ArrayList<String>();;
			try {
				propriedades = (List<String>) Arquivo.leArquivo(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (String propriedade : propriedades) {
				String[] dados = propriedade.split(";");
				itensPropriedade.put(dados[0],dados[1]);			
			}
			if (itensPropriedade.containsKey(nome)) {
				return itensPropriedade.get(nome);
			}
			return "";

	}
}
