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
import edu.sit.model.Cliente;

public class ClienteDao implements IDao<Cliente>, IInstaladorDao {

	@Override
	public boolean criaTabela() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE Cliente (" + " idCliente INT NOT NULL AUTO_INCREMENT,"
					+ " Nome VARCHAR(45) NOT NULL," + " CPF VARCHAR(14) NOT NULL,"
					+ " Data_Nascimento DATE NOT NULL," + " Endereco VARCHAR(45) NOT NULL,"
					+ " Contato_idContato INT NOT NULL," + " PRIMARY KEY (idCliente),"
					+ " INDEX fk_Cliente_Contato1_idx (Contato_idContato ASC))" + "ENGINE = InnoDB;");
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
			st.execute("DROP TABLE Cliente;");
			return true;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Cliente consulta(Integer idCliente) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Cliente WHERE idCliente = ?;");
			pst.setInt(1, idCliente);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? Cliente.consultaClienteBanco(rs.getInt("idCliente"), rs.getString("Nome"),
					rs.getDate("Data_Nascimento").toLocalDate(), rs.getString("CPF"), rs.getString("Endereco"),
					rs.getInt("Contato_idContato")) : null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Map<Integer, Cliente> consultaTodos() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		Map<Integer, Cliente> clientes = new HashMap<Integer, Cliente>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Cliente;");
			while (rs.next()) {
				clientes.put(Integer.valueOf(rs.getInt("idCliente")),
						Cliente.consultaClienteBanco(rs.getInt("idCliente"), rs.getString("Nome"),
								rs.getDate("Data_Nascimento").toLocalDate(), rs.getString("CPF"),
								rs.getString("Endereco"), rs.getInt("Contato_idContato")));
			}
			return clientes;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Cliente> consultaFaixa(Integer... codigos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Cliente> cliente = new ArrayList<Cliente>();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Cliente WHERE idCliente = ?;");
			for (Integer codigo : codigos) {
				try {
					pst.setInt(1, codigo);
					ResultSet rs = pst.executeQuery();
					if (rs.first()) {
						cliente.add(Cliente.consultaClienteBanco(rs.getInt("idCliente"), rs.getString("Nome"),
								rs.getDate("Data_Nascimento").toLocalDate(), rs.getString("CPF"),
								rs.getString("Endereco"), rs.getInt("Contato_idContato")));
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
		return cliente;
	}

	@Override
	public boolean insere(Cliente objeto) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Cliente (Nome, Data_Nascimento, CPF, Endereco, Contato_idContato) values (?, ?, ?, ?, ?);");
			pst.setString(1, objeto.getNome());
			pst.setDate(2, java.sql.Date.valueOf(objeto.getDataDeNascimento()));
			pst.setString(3, objeto.getCpf());
			pst.setString(4, objeto.getEndereco());
			pst.setInt(5, objeto.getContatoid());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Cliente> insereVarios(List<Cliente> objetos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Cliente> falhados = new ArrayList<>();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Cliente (Nome, Data_Nascimento, CPF, Endereco, Contato_idContato) values (?, ?, ?, ?, ?);");
			for (Cliente cliente : objetos) {
				try {
					pst.setString(1, cliente.getNome());
					pst.setDate(2, java.sql.Date.valueOf(cliente.getDataDeNascimento()));
					pst.setString(3, cliente.getCpf());
					pst.setString(4, cliente.getEndereco());
					pst.setInt(5, cliente.getContatoid());
					pst.executeUpdate();
				} catch (SQLException i) {
					new DaoException(EErrosDao.INSERE_DADO, i.getMessage(), this.getClass());
					falhados.add(cliente);
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
	public boolean insereVariosTransacao(List<Cliente> objetos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			conexao.setAutoCommit(false);
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Cliente (Nome, Data_Nascimento, CPF, Endereco, Contato_idContato) values (?, ?, ?, ?, ?);");
			for (Cliente cliente : objetos) {
				pst.setString(1, cliente.getNome());
				pst.setDate(2, java.sql.Date.valueOf(cliente.getDataDeNascimento()));
				pst.setString(3, cliente.getCpf());
				pst.setString(4, cliente.getEndereco());
				pst.setInt(5, cliente.getContatoid());
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
	public boolean altera(Cliente objeto) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"UPDATE Cliente SET Nome = ?, Data_Nascimento = ?, CPF = ?, Endereco = ?  WHERE idCliente = ?;");
			pst.setString(1, objeto.getNome());
			pst.setDate(2, java.sql.Date.valueOf(objeto.getDataDeNascimento()));
			pst.setString(3, objeto.getCpf());
			pst.setString(4, objeto.getEndereco());
			pst.setInt(5, objeto.getId());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.ALTERA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public boolean exclui (Integer... codigos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Cliente WHERE idCliente = ?;");
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
			ResultSet rs = st.executeQuery("SELECT MAX(idCliente) FROM Cliente;");
			return rs.first() ? rs.getInt(1) : 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.PEGA_ID, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public Cliente getFullProperty(Integer id) throws DaoException, ConexaoException {
		Cliente cliente = consulta(id);
		cliente.setContato(new ContatoDao().consulta(cliente.getContatoid()));
		return cliente;
	}
}
