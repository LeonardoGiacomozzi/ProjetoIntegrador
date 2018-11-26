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
import edu.sit.model.Produto;
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
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM venda  where id = ?;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			
			return rs.first() ? Venda.criaVenda(rs.getInt("id"),rs.getInt("Cliente"), 
					rs.getInt("Funcionario"),rs.getDouble("Valor")) : null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	public Venda consultaCompleta(Integer id) throws DaoException, ConexaoException {
		Venda venda = consulta(id);
		venda.setFuncionario(new FuncionarioDao().consulta(venda.getFuncionarioId()));
		venda.setCliente(new ClienteDao().consulta(venda.getClienteId()));
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

		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("INSERT INTO Venda (Cliente, Funcionario, Valor) values (?, ?, ?);");
			pst.setInt(1, objeto.getClienteId());
			pst.setInt(2, objeto.getFuncionarioId());
			pst.setDouble(3, objeto.getValor());
			System.out.println(pst.executeUpdate() > 0 ? "Venda inserida com sucesso" : "Erro ao inserir venda");
			for (Produto produto : objeto.getProdutos()) {

				PreparedStatement pst2 = conexao
						.prepareStatement("INSERT INTO ItensPedido (venda, Produtos) values (?, ?);");
				pst2.setInt(1, pegaUltimoID());
				pst2.setInt(2, produto.getId());
				System.out.println(pst2.executeUpdate() > 0
						? "produto " + produto.getNome() + " da venda " + pegaUltimoID() + " Inserido com sucesso!"
						: "Erro ao inserir produto" + produto.getNome());
			}
		} catch (Exception e) {
			throw new DaoException(EErrosDao.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}

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

	public Integer pegaUltimoID() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(id) FROM Venda;");
			return rs.first() ? rs.getInt(1) : 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.PEGA_ID, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	public ArrayList<Produto> listaProdutoVenda(Integer codigo) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM itensPedido  where venda = ?;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			ArrayList<Produto> itensPedido = new ArrayList<Produto>();
			while (rs.next()) {
				itensPedido.add(new ProdutoDao().consulta(rs.getInt("produtos")));
			}
			return itensPedido;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}


}
