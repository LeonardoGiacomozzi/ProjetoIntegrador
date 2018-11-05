package edu.sit.bancodedados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import edu.sit.bancodedados.conexao.Conexao;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.erros.dao.EErrosDao;
import edu.sit.model.Fornecedor;

public class FornecedorDao implements IDao<Fornecedor>, IInstaladorDao {

	@Override
	public boolean criaTabela() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE Fornecedor (" + " idCadastro_Fornecedor INT NOT NULL AUTO_INCREMENT," + 
					" Nome VARCHAR(45) NOT NULL," + " CNPJ VARCHAR(45) NOT NULL," + " Pessoa_Responsavel VARCHAR(50) NOT NULL," + 
					" Contato_idContato INT NOT NULL," + " PRIMARY KEY (idCadastro_Fornecedor)," + 
					" INDEX fk_Fornecedor_Contato1_idx (Contato_idContato ASC))" + " ENGINE = InnoDB;");
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
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Fornecedor WHERE idCadastro_Fornecedor = ?;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			return rs.first() ? Fornecedor.consultaFornecedorBanco(rs.getInt("idCadastro_Fornecedor"), rs.getString("Nome"),
					rs.getString("CNPJ"), rs.getString("Pessoa_Responsavel"), rs.getInt("Contato_idContato")) : null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Map<Integer, Fornecedor> consultaTodos() throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fornecedor> consultaFaixa(Integer... faixa) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insere(Fornecedor objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Fornecedor> insereVarios(List<Fornecedor> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insereVariosTransacao(List<Fornecedor> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altera(Fornecedor objeto) throws DaoException, ConexaoException {
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
