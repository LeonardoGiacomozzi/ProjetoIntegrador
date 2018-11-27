package edu.sit.bancodedados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.sit.bancodedados.conexao.Conexao;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.erros.dao.EErrosDao;
import edu.sit.model.Fornecedor;

public class FornecedorDao extends InstaladorDao implements IDao<Fornecedor> {

	@Override
	public boolean criaTabela() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE Fornecedor (" + "id INT NOT NULL AUTO_INCREMENT," + 
					  "Nome VARCHAR(45) NOT NULL," + "CNPJ VARCHAR(45) NOT NULL," + 
					  "PessoaResponsavel VARCHAR(45) NOT NULL," + "Contato INT NOT NULL," +
					  "PRIMARY KEY (id)," + "INDEX fk_Fornecedor_Contato1_idx (Contato ASC)) ENGINE = InnoDB;");
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
			st.execute("DROP TABLE Fornecedor;");
			return true;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Fornecedor consulta(Integer codigo) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Fornecedor WHERE id = ?;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? Fornecedor.consultaFornecedorBanco(rs.getInt("id"), rs.getString("Nome"),
					rs.getString("CNPJ"), rs.getString("PessoaResponsavel"), rs.getInt("Contato")) : null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public Fornecedor consultaCompleta(Integer id) throws DaoException, ConexaoException {
		Fornecedor fornecedor = consulta(id);
		fornecedor.setContato(new ContatoDao().consulta(fornecedor.getContatoid()));
		return fornecedor; 
	}
	
	public Fornecedor consultaCNPJ(String cnpj) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Fornecedor Where CNPJ = ?;");
			pst.setString(1, cnpj);
			ResultSet rs = pst.executeQuery();
			Fornecedor fornecedor = rs.first() ? Fornecedor.consultaFornecedorBanco(rs.getInt("id"), rs.getString("Nome"),
					rs.getString("CNPJ"), rs.getString("PessoaResponsavel"), rs.getInt("Contato")) : null;
			return consultaCompleta(fornecedor.getId());
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Fornecedor> consultaTodos() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Fornecedor;");
			while (rs.next()) {
				fornecedores.add(Fornecedor.consultaFornecedorBanco(rs.getInt("id"), rs.getString("Nome"), 
								rs.getString("CNPJ"), rs.getString("PessoaResponsavel"), rs.getInt("Contato")));
			}
			return fornecedores;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public List<Fornecedor> consultaTodosCompleto() throws DaoException, ConexaoException {
		List<Fornecedor> fornecedoresCompleto = new ArrayList<>();
		List<Fornecedor> fornecedores = consultaTodos();
		for (Fornecedor fornecedor : fornecedores) {
			fornecedoresCompleto.add(consultaCompleta(fornecedor.getId()));
		}
		return fornecedoresCompleto;
	}

	@Override
	public List<Fornecedor> consultaFaixa(Integer... codigos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Fornecedor> fornecedor = new ArrayList<Fornecedor>();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Fornecedor WHERE id = ?;");
			for (Integer codigo : codigos) {
				try {
					pst.setInt(1, codigo);
					ResultSet rs = pst.executeQuery();
					if (rs.first()) {
						fornecedor.add(Fornecedor.consultaFornecedorBanco(rs.getInt("id"), rs.getString("Nome"), 
								rs.getString("CNPJ"), rs.getString("PessoaResponsavel"), rs.getInt("Contato")));
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
		return fornecedor;
	}
	
	public List<Fornecedor> consultaFaixaCompleto(Integer... codigos) throws DaoException, ConexaoException {
		List<Fornecedor> fornecedoresCompleto = new ArrayList<>();
		List<Fornecedor> fornecedores = consultaFaixa(codigos);
		for (Fornecedor fornecedor : fornecedores) {
			fornecedoresCompleto.add(consultaCompleta(fornecedor.getId()));
		}
		return fornecedoresCompleto;
	}

	@Override
	public boolean insere(Fornecedor objeto) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Fornecedor (Nome, CNPJ, PessoaResponsavel, Contato) values (?, ?, ?, ?);");
			pst.setString(1, objeto.getNome());
			pst.setString(2, objeto.getCNPJ());
			pst.setString(3, objeto.getPessoaResponsavel());
			pst.setInt(4, objeto.getContatoid());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Fornecedor> insereVarios(List<Fornecedor> objetos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Fornecedor> falhados = new ArrayList<>();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Fornecedor (Nome, CPNJ, PessoaResponsavel, Contato) values (?, ?, ?, ?);");
			for (Fornecedor fornecedor : objetos) {
				try {
					pst.setString(1, fornecedor.getNome());
					pst.setString(2, fornecedor.getCNPJ());
					pst.setString(3, fornecedor.getPessoaResponsavel());
					pst.setInt(5, fornecedor.getContatoid());
					pst.executeUpdate();
				} catch (SQLException i) {
					new DaoException(EErrosDao.INSERE_DADO, i.getMessage(), this.getClass());
					falhados.add(fornecedor);
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
	public boolean insereVariosTransacao(List<Fornecedor> objetos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			conexao.setAutoCommit(false);
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Fornecedor (Nome, CNPJ, PessoaResponsavel, Contato) values (?, ?, ?, ?);");
			for (Fornecedor fornecedor : objetos) {
				pst.setString(1, fornecedor.getNome());
				pst.setString(2, fornecedor.getCNPJ());
				pst.setString(3, fornecedor.getPessoaResponsavel());
				pst.setInt(5, fornecedor.getContatoid());
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
	public boolean altera(Fornecedor objeto) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"UPDATE Fornecedor SET Nome = ?, CNPJ = ?, PessoaResponsavel = ?  WHERE id = ?;");
			pst.setString(1, objeto.getNome());
			pst.setString(2, objeto.getCNPJ());
			pst.setString(3, objeto.getPessoaResponsavel());
			pst.setInt(4, objeto.getId());
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
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Fornecedor WHERE id = ?;");
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
			ResultSet rs = st.executeQuery("SELECT MAX(id) FROM Fornecedor;");
			return rs.first() ? rs.getInt(1) : 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.PEGA_ID, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
}
