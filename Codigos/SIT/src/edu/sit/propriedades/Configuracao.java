package edu.sit.propriedades;

import java.util.ArrayList;

import edu.sit.uteis.Arquivo;
import edu.sit.uteis.cadastro.UtilCadastro;

public class Configuracao {
	
	public boolean setPropriedades() {

		String banco = "banco;" + UtilCadastro.pedeNome("Informe o nome do banco de dados");
		String usuario = "usuario;" + UtilCadastro.pedeNome("Informe o nome do usuario do banco de dados");
		String senha = "senha;" + UtilCadastro.pedeNome("Informe a senha de acesso");
		String localNotas = "localNota;" + UtilCadastro.pedeNome("Informe o local onde deseja salvar as notas fiscais");

		ArrayList<String> props = new ArrayList<>();
		props.add(banco);
		props.add(usuario);
		props.add(senha);
		props.add(localNotas);

		Arquivo.gravaArquivo("propiedades.txt", props, false);
		return true;
	}

}
