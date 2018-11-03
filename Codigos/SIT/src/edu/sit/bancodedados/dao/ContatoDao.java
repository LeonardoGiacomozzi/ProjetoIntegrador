package edu.sit.bancodedados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Contato WHERE idContato = ?;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? Contato.consultaContatoBanco(rs.getInt("idContato"), 
					rs.getString("Telefone"), rs.getString("Email")) : null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
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
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Contato (Telefone, Email) values (?, ?);");
			pst.setString(1, objeto.getTelefone());
			pst.setString(2, objeto.getEmail());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Contato> insereVarios(List<Contato> objetos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Contato> falhados = new ArrayList<>();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Contato (Telefone, Email) values (?, ?);");
			for (Contato contato : objetos) {
				try {
					pst.setString(1, contato.getTelefone());
					pst.setString(2, contato.getEmail());
					pst.executeUpdate();
				} catch (SQLException i) {
					new DaoException(EErrosDao.INSERE_DADO, i.getMessage(), this.getClass());
					falhados.add(contato);
				}
			}
		} catch (Exception e) {
			throw new DaoException(EErrosDao.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
		return falhados;
	}

	@Override
	public boolean insereVariosTransacao(List<Contato> objetos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			conexao.setAutoCommit(false);
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Contato (Telefone, Email) values (?, ?);");
			for (Contato contato : objetos) {
				pst.setString(1, contato.getTelefone());
				pst.setString(2, contato.getEmail());
				pst.executeUpdate();
			}
			conexao.commit();
			return true;
		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (Exception r) {
				throw new DaoException(EErrosDao.ROLLBACK, e.getMessage(), this.getClass());
			}
			throw new DaoException(EErrosDao.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
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
