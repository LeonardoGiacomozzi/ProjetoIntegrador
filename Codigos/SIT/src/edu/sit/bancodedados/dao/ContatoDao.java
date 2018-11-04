package edu.sit.bancodedados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
		Connection conexao = Conexao.abreConexao();
		Map<Integer, Contato> contatos = new HashMap<Integer, Contato>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Contato;");
			while (rs.next()) {
				contatos.put(Integer.valueOf(rs.getInt("idContato")),
						Contato.consultaContatoBanco(rs.getInt("idContato"), rs.getString("Telefone"), rs.getString("Email")));
			}
			return contatos;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Contato> consultaFaixa(Integer... codigos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Contato> contato = new ArrayList<Contato>();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Contato WHERE idContato = ?;");
			for (Integer codigo : codigos) {
				try {
					pst.setInt(1, codigo);
					ResultSet rs = pst.executeQuery();
					if (rs.first()) {
						contato.add(Contato.consultaContatoBanco(rs.getInt("idContato"), rs.getString("Telefone"), rs.getString("Email")));
					}
				} catch (Exception c) {
					new DaoException(EErrosDao.CONSULTA_DADO, c.getMessage(), this.getClass());
				}
			}
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
		return contato;
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
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"UPDATE Contato SET Telefone = ?, Email = ? WHERE idContato = ?;");
			pst.setString(1, objeto.getTelefone());
			pst.setString(2, objeto.getEmail());
			pst.setInt(3, objeto.getId());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.ALTERA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public boolean exclui(Integer... codigos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Contato WHERE idContato = ?;");
			for (Integer novo : codigos) {
				pst.setInt(1, novo);
				pst.execute();
			}
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
		return true;
	}
	
	@Override
	public Integer pegaUltimoID() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(idContato) FROM Contato;");
			return rs.first() ? rs.getInt(1) : 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.PEGA_ID, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
}
