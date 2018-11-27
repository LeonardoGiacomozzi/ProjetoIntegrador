package edu.sit.bancodedados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import edu.sit.bancodedados.conexao.Conexao;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.erros.dao.EErrosDao;
import edu.sit.model.NotaFiscal;

public class NotaFiscalDao extends InstaladorDao implements IDao<NotaFiscal> {

	@Override
	public boolean criaTabela() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE NotaFiscal (" + "id INT NOT NULL AUTO_INCREMENT, " + "DataEmissao DATE NOT NULL," +
					  "Total DOUBLE NOT NULL," + "Venda INT NOT NULL," + "PRIMARY KEY (id)," + 
					  "INDEX fk_NotaFiscal_Venda1_idx (Venda ASC)) ENGINE = InnoDB;");
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
			st.execute("DROP TABLE NotaFiscal;");
			return true;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public NotaFiscal consulta(Integer codigo) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		Calendar dataLida = Calendar.getInstance();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM NotaFiscal WHERE id = ?;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			dataLida.setTime(rs.getDate("DataEmissao"));
			return rs.first() ? NotaFiscal.consultaNotaBanco(rs.getInt("id"), dataLida, 
					rs.getDouble("Total"), rs.getInt("Venda")) : null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<NotaFiscal> consultaTodos() throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NotaFiscal> consultaVariosPorID(Integer... faixa) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insere(NotaFiscal objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altera(NotaFiscal objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exclui(Integer... codigos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM NotaFiscal WHERE id = ?;");
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
			ResultSet rs = st.executeQuery("SELECT MAX(id) FROM NotaFiscal;");
			return rs.first() ? rs.getInt(1) : 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.PEGA_ID, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
}
