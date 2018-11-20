package edu.sit.bancodedados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import edu.sit.bancodedados.conexao.Conexao;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.erros.dao.EErrosDao;
import edu.sit.model.Venda;

public class VendaDao implements IDao<Venda>, IInstaladorDao {

	@Override
	public boolean criaTabela() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE Venda (" + " idVenda INT NOT NULL AUTO_INCREMENT,"
					+ " Valor DOUBLE NOT NULL," + " Funcionario_idCadastro_Funcionario INT NOT NULL,"
					+ " Nota_Fiscal_idNota_Fiscal INT NOT NULL," + " PRIMARY KEY (idVenda),"
					+ " INDEX fk_Venda_Funcionário1_idx (Funcionario_idCadastro_Funcionario ASC),"
					+ " INDEX fk_Venda_Nota_Fiscal1_idx (Nota_Fiscal_idNota_Fiscal ASC))" + " ENGINE = InnoDB;");
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
			st.execute("DROP TABLE Venda;");
			return true;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Venda consulta(Integer codigo) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("SELECT * FROM Venda WHERE idVenda = ?;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			return rs.first()
					? Venda.consultaVendaBanco(rs.getInt("idVenda"), rs.getDouble("Valor"), 
							rs.getInt("Funcionario_idCadastro_Funcionario"), rs.getInt("Nota_Fiscal_idNota_Fiscal"))
					: null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	public Venda consultaCompleta(Integer id) throws DaoException, ConexaoException {
		Venda venda = consulta(id);
		venda.setFuncionario(new FuncionarioDao().consulta(venda.getFuncionarioId()));
		venda.setNotaFiscal(new NotaFiscalDao().consulta(venda.getNotaFiscalId()));
		return venda;
	}

	@Override
	public List<Venda> consultaTodos() throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venda> consultaFaixa(Integer... faixa) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insere(Venda objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Venda> insereVarios(List<Venda> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insereVariosTransacao(List<Venda> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altera(Venda objeto) throws DaoException, ConexaoException {
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
