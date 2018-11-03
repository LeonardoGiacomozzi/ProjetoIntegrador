package edu.sit.bancodedados.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import edu.sit.bancodedados.conexao.Conexao;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.model.Contato;

public class ContatoDao implements IDao<Contato>, IInstaladorDao {

	@Override
	public boolean criaTabela() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE Contato (" + " idContato INT NOT NULL AUTO_INCREMENT," + 
					" Telefone VARCHAR(15) NOT NULL," + " Email VARCHAR(45) NOT NULL," + 
					" PRIMARY KEY (idContato))" + " ENGINE = InnoDB;");
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
			st.execute("DROP TABLE Contato;");
			return true;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Contato consulta(Integer codigo) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, Contato> consultaTodos() throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contato> consultaFaixa(Integer... faixa) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insere(Contato objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Contato> insereVarios(List<Contato> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insereVariosTransacao(List<Contato> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altera(Contato objeto) throws DaoException, ConexaoException {
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
