package edu.sit.controller.cadastro;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.CategoriaDao;
import edu.sit.erro.cadastro.CadastroExeption;
import edu.sit.erro.cadastro.EErroCadastro;
import edu.sit.erro.editor.EErroEdicao;
import edu.sit.erro.editor.EdicaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Categoria;
import edu.sit.uteis.cadastro.UtilCadastro;

public class CategoriaController {

	public static boolean cadastro() throws CadastroExeption {
		String nome = null;
		System.out.print("*****CADASTRO DE CATEGORIA*****");
		nome = UtilCadastro.pedeNome();
		try {
			Categoria categoria = Categoria.criaCategoria(nome);
			new CategoriaDao().insere(categoria);
			
		} catch (DaoException | ConexaoException e) {
			System.out.println(e.getMessage());
			throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_CATEGORIA);
		}
		return false;
	}
	
	public static boolean editar(Integer codigo) throws EdicaoException {
		try {
			Categoria categoriaBanco = new CategoriaDao().consulta(codigo);
			
			System.out.print("*****EDITOR DE CATEGORIA*****");	Integer opcao = 99;
			while (opcao != 0) {
				System.out.println("\n\tSELECIONE O ITEM QUE DESEJA EDITAR\t:");
				System.out.println("\n\t\t1----------NOME\t"+categoriaBanco.getNome());
				System.out.println("\n\t\t0----------FINALIZAR");
				System.out.println("\n\n\t\t---:");

				switch (opcao) {
				case 1:
					produtoBanco.setNome(UtilCadastro.pedeNome());

					break;
				case 2:
					produtoBanco.setCategoriaId(UtilCadastro.pedeCategoria());
					break;
				case 3:
					produtoBanco.setFornecedorId(UtilCadastro.pedeFornecedor());
					break;
				case 4:
					produtoBanco.setValorUnitario(UtilCadastro.pedeValorUnitario());
					break;
			categoriaBanco.setNome(UtilCadastro.pedeNome());
			try {
				new CategoriaDao().altera(categoriaBanco);
				
			} catch (DaoException e) {
				System.out.println(e.getMessage());
				throw new CadastroExeption(EErroCadastro.ERRO_CADASTRO_CATEGORIA);
			}
			return true;
		} catch (DaoException | ConexaoException | CadastroExeption e) {
			System.out.println(e.getMessage());
			throw new EdicaoException(EErroEdicao.ERRO_BUSCA_CATEGORIA);
		}

	}




}
