package edu.sit.bancodedados.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import edu.sit.bancodedados.conexao.Conexao;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.model.Produto;

public class ProdutoDao implements IDao<Produto>, IInstaladorDao {

	@Override
	public boolean criaTabela() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE Produtos (" + " idProdutos INT NOT NULL AUTO_INCREMENT," + 
					" Nome VARCHAR(45) NOT NULL," + " Categoria VARCHAR(15) NOT NULL," + " Quantidade INT(80) NOT NULL," + 
					" Valor DOUBLE NOT NULL," + " PRIMARY KEY (idProdutos))" + " ENGINE = InnoDB;");
			return true;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CRIAR_TABELA, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public boolean excluiTabela() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.execute("DROP TABLE Produtos;");
			return true;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Produto consulta(Integer codigo) throws DaoException, ConexaoException {
		return null;
	}

	@Override
	public Map<Integer, Produto> consultaTodos() throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produto> consultaFaixa(Integer... faixa) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insere(Produto objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Produto> insereVarios(List<Produto> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insereVariosTransacao(List<Produto> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altera(Produto objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exclui(Integer... codigos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer pegaUltimoID() throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}
}
