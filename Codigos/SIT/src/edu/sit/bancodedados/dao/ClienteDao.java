package edu.sit.bancodedados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			st.executeUpdate("CREATE TABLE IF NOT EXISTS `TESTE`.`Cliente` (\r\n" + 
					"  `idCliente` INT NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `Nome` VARCHAR(45) NOT NULL,\r\n" + 
					"  `CPF` VARCHAR(14) NOT NULL,\r\n" + 
					"  `Data_Nascimento` DATE NOT NULL,\r\n" + 
					"  `Endereco` VARCHAR(45) NOT NULL,\r\n" + 
					"  `Contato_idContato` INT NOT NULL,\r\n" + 
					"  PRIMARY KEY (`idCliente`),\r\n" + 
					"  INDEX `fk_Cliente_Contato1_idx` (`Contato_idContato` ASC))\r\n" + 
					"ENGINE = InnoDB;");
			return true;
		} catch (SQLException e) {
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
		} catch (SQLException e){
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Cliente consulta(Integer codigo) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Teste1 WHERE idCliente = ?;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			return rs.first() ?  Cliente.criaPessoaBanco(rs.getInt("idCliente"),
										   rs.getString("Nome"),
										   rs.getDate("Data_Nascimento"),
										   rs.getString("CPF"),
										   rs.getString("Endereco"),
										   rs.getInt("Contato_idContato")) : null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Map<Integer, Cliente> consultaTodos() throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> consultaFaixa(Integer... faixa) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insere(Cliente objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cliente> insereVarios(Map<Integer, Cliente> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> insereVarios(List<Cliente> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insereVariosTransacao(List<Cliente> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altera(Cliente objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exclui(Integer codigo) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exclui(Cliente objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
