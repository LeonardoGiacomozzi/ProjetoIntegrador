package edu.sit.view.configuracao;

import edu.sit.controller.cadastro.ConfiguracaoController;
import edu.sit.erro.leitura.LeituraException;
import edu.sit.erro.propriedades.PropriedadesException;
import edu.sit.propriedades.Configuracao;
import edu.sit.uteis.Leitor;
import edu.sit.uteis.cadastro.UtilCadastro;

public class ConfiguracaoView {

	public static void editar() {

		Integer op = Integer.MAX_VALUE;
		String[] props = new String[3];
		exibeConfig();
		while (op != 0) {

			System.out.println("\nQual propriedade você deseja alterar: ");
			System.out.println("1 - Nome de úsuario do banco");
			System.out.println("2 - Senha do banco");
			System.out.println("3 - Local onde salva as notas");
			System.out.println("0 - Salvar e sair");
			System.out.print("\nInforme a opção desejada: \t");

			try {
				op = Leitor.leInteger();
			} catch (LeituraException e) {
				System.out.println(e.getMessage());
			}
			switch (op) {
			case 1:
				props[0] = UtilCadastro.pedeNome("Informe o nome do úsuario do banco: \t");
				break;
			case 2:
				props[1] = UtilCadastro.pedeNome("Informe a senha do banco: \t");
				break;
			case 3:
				props[2] = UtilCadastro.pedeNome("Informe o local onde deseja salvar as notas: \t");
				break;
			case 0:
				try {
					ConfiguracaoController.editar(props);
					System.out.println("Novas configurações salvas com SUCESSO!");
					
				} catch (PropriedadesException e) {
					System.out.println(e.getMessage()+"\n Não foi possivel salvar as alterações...");
				}
				break;

			default:
					System.out.println("Informe um valor válido...");
				break;
			}
			
		}



	}

	public static void exibeConfig() {
		System.out.println("\nConfigurações atuais: ");
		System.out.println("Nome do banco de dados: ["+Configuracao.getPropriedade("banco") + "]");
		System.out.println("Nome do usuário do banco: ["+Configuracao.getPropriedade("usuario") + "]");
		System.out.println("Senha do banco: ["+Configuracao.getPropriedade("senha") + "]");
		System.out.println("Local onde é salvo as notas fiscais: ["+Configuracao.getPropriedade("localNota") + "]");
	}
}
