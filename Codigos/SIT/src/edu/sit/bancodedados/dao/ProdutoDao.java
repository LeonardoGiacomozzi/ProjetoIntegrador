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
import edu.sit.model.Produto;

public class ProdutoDao implements IDao<Produto>, IInstaladorDao {

	@Override
	public boolean criaTabela() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE Produtos (" + "id INT NOT NULL AUTO_INCREMENT," + "Nome VARCHAR(45) NOT NULL," +
					  "Quantidade INT(80) NOT NULL," + "Valor DOUBLE NOT NULL," + "Fornecedor INT NOT NULL," + 
					  "Categoria INT NOT NULL," + " PRIMARY KEY (id)," + "INDEX fk_Produtos_Fornecedor1_idx (Fornecedor ASC)," +
					  "INDEX fk_Produtos_Categoria1_idx (Categoria ASC)) ENGINE = InnoDB;");
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
			st.execute("DROP TABLE Produtos;");
			return true;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public Produto consulta(Integer codigo) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Produtos WHERE id = ?;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
				return rs.first() ? Produto.consultaProdutoBanco(rs.getInt("id"), rs.getString("Nome"),
					rs.getInt("Quantidade"), rs.getDouble("Valor"), rs.getInt("Fornecedor"), 
					rs.getInt("Categoria")) : null;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public Produto consultaCompleta(Integer id) throws DaoException, ConexaoException {
		Produto produto = consulta(id);
		produto.setFornecedor(new FornecedorDao().consultaCompleta(produto.getFornecedorId()));
		produto.setCategoria(new CategoriaDao().consulta(produto.getCategoriaId()));
		return produto; 
	}

	@Override
	public List<Produto> consultaTodos() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Produtos;");
			while (rs.next()) {
				produtos.add(Produto.consultaProdutoBanco(rs.getInt("id"), rs.getString("Nome"),
						rs.getInt("Quantidade"), rs.getDouble("Valor"), rs.getInt("Fornecedor"), 
						rs.getInt("Categoria")));
			}
			return produtos;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public List<Produto> consultaTodosCompleto() throws DaoException, ConexaoException {
		List<Produto> produtosCompleto = new ArrayList<>();
		List<Produto> produtos = consultaTodos();
		for (Produto produto : produtos) {
			produtosCompleto.add(consultaCompleta(produto.getId()));
		}
		return produtosCompleto;
	}

	@Override
	public List<Produto> consultaFaixa(Integer... codigos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Produto> produto = new ArrayList<Produto>();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM Produtos WHERE id = ?;");
			for (Integer codigo : codigos) {
				try {
					pst.setInt(1, codigo);
					ResultSet rs = pst.executeQuery();
					if (rs.first()) {
						produto.add(Produto.consultaProdutoBanco(rs.getInt("id"), rs.getString("Nome"),
								rs.getInt("Quantidade"), rs.getDouble("Valor"), rs.getInt("Fornecedor"), 
								rs.getInt("Categoria")));
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
		return produto;
	}
	
	public List<Produto> consultaFaixaCompleto(Integer... codigos) throws DaoException, ConexaoException {
		List<Produto> produtosCompleto = new ArrayList<>();
		List<Produto> produtos = consultaFaixa(codigos);
		for (Produto produto : produtos) {
			produtosCompleto.add(consultaCompleta(produto.getId()));
		}
		return produtosCompleto;
	}

	@Override
	public boolean insere(Produto objeto) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Produto (Nome, Quantidade, Valor, Forncedor, Categoria) values (?, ?, ?, ?, ?);");
			pst.setString(1, objeto.getNome());
			pst.setInt(2, objeto.getQuantidade());
			pst.setDouble(3, objeto.getValorUnitario());
			pst.setInt(4, objeto.getFornecedorId());
			pst.setInt(5, objeto.getCategoriaId());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	@Override
	public List<Produto> insereVarios(List<Produto> objetos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		List<Produto> falhados = new ArrayList<>();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Produto (Nome, Quantidade, Valor, Forncedor, Categoria) values (?, ?, ?, ?, ?);");
			for (Produto produto : objetos) {
				try {
					pst.setString(1, produto.getNome());
					pst.setInt(2, produto.getQuantidade());
					pst.setDouble(3, produto.getValorUnitario());
					pst.setInt(4, produto.getFornecedorId());
					pst.setInt(5, produto.getCategoriaId());
					pst.executeUpdate();
				} catch (SQLException i) {
					new DaoException(EErrosDao.INSERE_DADO, i.getMessage(), this.getClass());
					falhados.add(produto);
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
	public boolean insereVariosTransacao(List<Produto> objetos) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			conexao.setAutoCommit(false);
			PreparedStatement pst = conexao.prepareStatement(
					"INSERT INTO Produto (Nome, Quantidade, Valor, Forncedor, Categoria) values (?, ?, ?, ?, ?);");
			for (Produto produto : objetos) {
				pst.setString(1, produto.getNome());
				pst.setInt(2, produto.getQuantidade());
				pst.setDouble(3, produto.getValorUnitario());
				pst.setInt(4, produto.getFornecedorId());
				pst.setInt(5, produto.getCategoriaId());
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
	public boolean altera(Produto objeto) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement(
					"UPDATE Produto SET Nome = ?, Quantidade = ?, Valor = ?  WHERE id = ?;");
			pst.setString(1, objeto.getNome());
			pst.setInt(2, objeto.getQuantidade());
			pst.setDouble(3, objeto.getValorUnitario());
			pst.setInt(5, objeto.getId());
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
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM Produtos WHERE id = ?;");
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
			ResultSet rs = st.executeQuery("SELECT MAX(id) FROM Produtos;");
			return rs.first() ? rs.getInt(1) : 0;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.PEGA_ID, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
}
