package edu.sit.view.configuracao;

import edu.sit.controller.cadastro.ConfiguracaoController;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.propriedades.PropriedadesException;
import edu.sit.propriedades.Configuracao;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;

public class ConfiguracaoView {

	public static void editar() {

		Integer op = null;
		String[] props = new String[3];
		exibeConfig();
		while (op != 0) {

			System.out.println("Qual propriedade você deseja alterar: ");
			System.out.println("1- Nome de úsuario do banco");
			System.out.println("2- Senha do banco");
			System.out.println("3- Local onde salva as notas");
			System.out.println("0- Salvar e sair");

			try {
				op = Leitor.leInteger();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
			switch (op) {
			case 1:
				props[0] = UtilCadastro.pedeNome("Informe o nome do úsuario do banco");
				break;
			case 2:
				props[1] = UtilCadastro.pedeNome("Informe a senha do banco");
				break;
			case 3:
				props[2] = UtilCadastro.pedeNome("Informe o local onde deseja salvar as notas");
				break;
			case 0:
				try {
					ConfiguracaoController.editar(props);
					System.out.println("Novas configurações salvas");
					
				} catch (PropriedadesException e) {
					System.out.println(e.getMessage()+"\n Não foi possivel salvar as alterações");
				}
				break;

			default:
					System.out.println("Informe um valor valido");
				break;
			}
			
		}



	}

	public static void exibeConfig() {
		
		
		System.out.println("Configurações atuais");
		System.out.println("Nome do banco de dados"+Configuracao.getPropriedade("banco"));
		System.out.println("Nome do úsuario do banco"+Configuracao.getPropriedade("usuario"));
		System.out.println("Senha do bando"+Configuracao.getPropriedade("senha"));
		System.out.println("Local onde é salvo as notas fiscais"+Configuracao.getPropriedade("localNota"));
	}
}
