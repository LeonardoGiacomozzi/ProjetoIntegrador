package edu.sit.bancodedados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.sit.bancodedados.conexao.Conexao;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.erros.dao.EErrosDao;
import edu.sit.model.Usuario;

public class UsuarioDao extends InstaladorDao implements IDao<Usuario> {

	
	@Override
	public boolean criaTabela() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE IF NOT EXISTS Usuarios (" 
					+ " id INT NOT NULL AUTO_INCREMENT," 
					+ " Login VARCHAR(45) NOT NULL," 
					+ " Senha VARCHAR(45) NOT NULL," 
					+ " PRIMARY KEY (id));");
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
			st.execute("DROP TABLE Usuarios;");
			return true;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Usuario consulta(Integer codigo) throws DaoException, ConexaoException {

		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Usuarios WHERE id = ?;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? Usuario.criaUsuario(rs.getInt("id"), 
					rs.getString("login"), rs.getString("senha")) : null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public Usuario consulta(String login) throws DaoException, ConexaoException {

		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Usuarios WHERE Login LIKE ?" + 
					"");
			pst.setString(1,login);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? Usuario.criaUsuario(rs.getInt("id"), 
					rs.getString("Login"), rs.getString("Senha")) : null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	@Override
	public List<Usuario> consultaTodos() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Usuarios WHERE id > 1;");
			while (rs.next()) {
				usuarios.add(Usuario.criaUsuario(rs.getInt("id"), rs.getString("Login"), 
								rs.getString("Senha")));
			}
			return usuarios;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Usuario> consultaVariosPorID(Integer... codigos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Usuario> usuario = new ArrayList<Usuario>();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Usuarios WHERE id = ?;");
			for (Integer codigo : codigos) {
				try {
					pst.setInt(1, codigo);
					ResultSet rs = pst.executeQuery();
					if (rs.first()) {
						usuario.add(Usuario.criaUsuario(rs.getInt("id"), rs.getString("Login"), 
								rs.getString("Senha")));
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
		return usuario;
	}

	@Override
	public boolean insere(Usuario objeto) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Usuarios (Login, Senha) VALUES (?, ?);");
			pst.setString(1, objeto.getLogin());
			pst.setString(2, objeto.getSenha());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public boolean altera(Usuario objeto) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"UPDATE Usuarios SET Login = ?, Senha = ? WHERE id = ?;");
			pst.setString(1, objeto.getLogin());
			pst.setString(2, objeto.getSenha());
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
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Usuarios WHERE id = ?;");
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
}
