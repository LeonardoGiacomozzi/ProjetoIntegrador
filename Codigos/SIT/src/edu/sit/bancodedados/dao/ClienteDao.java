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
import edu.sit.model.Cliente;

public class ClienteDao extends InstaladorDao implements IDao<Cliente> {

	@Override
	public boolean criaTabela() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE Cliente (" 
					+ "id INT NOT NULL AUTO_INCREMENT," 
					+ "Nome VARCHAR(45) NOT NULL," 
					+ "CPF VARCHAR(14) NOT NULL," 
					+ "DataNascimento DATE NOT NULL," 
					+ "Endereco VARCHAR(45) NOT NULL," 
					+ "Contato INT NOT NULL," + "PRIMARY KEY (id)," 
					+ "INDEX fk_Cliente_Contato1_idx (Contato ASC))"
					+ "ENGINE = InnoDB;");
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
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Cliente WHERE id = ?;");
			pst.setInt(1, idCliente);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? Cliente.consultaClienteBanco(rs.getInt("id"), rs.getString("Nome"),
					rs.getDate("DataNascimento").toLocalDate(), rs.getString("CPF"),rs.getString("Endereco"),
					rs.getInt("Contato")) : null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public Cliente consultaCompleta(Integer id) throws DaoException, ConexaoException {
		Cliente cliente = consulta(id);
		cliente.setContato(new ContatoDao().consulta(cliente.getContatoid()));
		return cliente;
	}
	
	public Cliente consultaCPF(String cpf) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Cliente WHERE CPF = ?;");
			pst.setString(1, cpf);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? Cliente.consultaClienteBanco(rs.getInt("id"), rs.getString("Nome"),
					rs.getDate("DataNascimento").toLocalDate(), rs.getString("CPF"), rs.getString("Endereco"),
					rs.getInt("Contato")) : null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Cliente> consultaTodos() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Cliente;");
			while (rs.next()) {
				clientes.add(Cliente.consultaClienteBanco(rs.getInt("id"), rs.getString("Nome"),
								rs.getDate("DataNascimento").toLocalDate(), rs.getString("CPF"),
								rs.getString("Endereco"), rs.getInt("Contato")));
			}
			return clientes;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public List<Cliente> consultaTodosCompleto() throws DaoException, ConexaoException {
		List<Cliente> clientesCompleto = new ArrayList<>();
		List<Cliente> clientes = consultaTodos();
		for (Cliente cliente : clientes) {
			clientesCompleto.add(consultaCompleta(cliente.getId()));
		}
		return clientesCompleto;
	}

	@Override
	public List<Cliente> consultaVariosPorID(Integer... codigos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Cliente> cliente = new ArrayList<Cliente>();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Cliente WHERE id = ?;");
			for (Integer codigo : codigos) {
				try {
					pst.setInt(1, codigo);
					ResultSet rs = pst.executeQuery();
					if (rs.first()) {
						cliente.add(Cliente.consultaClienteBanco(rs.getInt("id"), rs.getString("Nome"),
								rs.getDate("DataNascimento").toLocalDate(), rs.getString("CPF"),
								rs.getString("Endereco"), rs.getInt("Contato")));
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
	
	public List<Cliente> consultaFaixaCompleto(Integer... codigos) throws DaoException, ConexaoException {
		List<Cliente> clientesCompleto = new ArrayList<>();
		List<Cliente> clientes = consultaVariosPorID(codigos);
		for (Cliente cliente : clientes) {
			clientesCompleto.add(consultaCompleta(cliente.getId()));
		}
		return clientesCompleto;
	}

	@Override
	public boolean insere(Cliente objeto) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Cliente (Nome, DataNascimento, CPF, Endereco, Contato) values (?, ?, ?, ?, ?);");
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
	public boolean altera(Cliente objeto) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"UPDATE Cliente SET Nome = ?, DataNascimento = ?, CPF = ?, Endereco = ?  WHERE id = ?;");
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
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Cliente WHERE id = ?;");
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

	public Integer pegaUltimoID() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(id) FROM Cliente;");
			return rs.first() ? rs.getInt(1) : 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.PEGA_ID, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
}
