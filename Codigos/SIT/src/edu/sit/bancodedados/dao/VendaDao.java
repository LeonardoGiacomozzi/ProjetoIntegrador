package edu.sit.bancodedados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import edu.sit.DataObject.ProdutoQuantidade;
import edu.sit.bancodedados.conexao.Conexao;
import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.erros.dao.DaoException;
import edu.sit.erros.dao.EErrosDao;
import edu.sit.model.Venda;

public class VendaDao extends InstaladorDao {

	@Override
	public boolean criaTabela() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("CREATE TABLE IF NOT EXISTS `Venda` (" + 
					"  `id` INT NOT NULL AUTO_INCREMENT," + 
					"  `Valor` DOUBLE NOT NULL," + 
					"  `Funcionario` INT NOT NULL," + 
					"  `Cliente` INT NOT NULL," + 
					"	 `dataVenda` DATE NOT NULL,"+
					"  PRIMARY KEY (`id`)," + 
					"  INDEX `fk_Venda_Funcionário1_idx` (`Funcionario` ASC) ," + 
					"  INDEX `fk_Venda_Cliente1_idx` (`Cliente` ASC) )" + 
					"ENGINE = InnoDB;");
			
			st.executeUpdate("CREATE TABLE IF NOT EXISTS ItensPedido (" 
					+ " Produtos INT NOT NULL," 
					+ " Venda INT NOT NULL,"
					+ " Quantidade INT NOT NULL,"
					+ " PRIMARY KEY (Produtos, Venda)," 
					+ " INDEX fk_Produtos_has_Venda_Venda1_idx (Venda ASC)," 
					+ "	INDEX fk_Produtos_has_Venda_Produtos1_idx (Produtos ASC))" 
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
			st.execute("DROP TABLE Venda;");
			return true;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}

	public Venda consulta(Integer codigo) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM venda  where id = ?;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			
			return rs.first() ? Venda.criaVenda(rs.getInt("id"),rs.getInt("Cliente"), 
					rs.getInt("Funcionario"),rs.getDouble("Valor"),rs.getDate("dataVenda").toLocalDate()) : null;
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
		venda.setProdutos(listaProdutoVenda(id));
		
		
		return venda;
	}

	public boolean insere(Venda objeto) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao
					.prepareStatement("INSERT INTO Venda (Cliente, Funcionario, Valor,dataVenda) VALUES (?, ?, ?,?);");
			pst.setInt(1, objeto.getClienteId());
			pst.setInt(2, objeto.getFuncionarioId());
			pst.setDouble(3, objeto.getValor());
			pst.setDate(4,  java.sql.Date.valueOf(objeto.getDataVenda()));
			pst.executeUpdate();
			ArrayList<ProdutoQuantidade>  produtos = objeto.getProdutos();
			
			
			HashMap<Integer,ProdutoQuantidade> listaAux = new HashMap<>();
			for (ProdutoQuantidade produtoQuantidade : produtos) {
				if (listaAux.containsKey(produtoQuantidade.getItensPedido().getId())) {
					listaAux.get(produtoQuantidade.getItensPedido().getId())
						.setQuantidadeProduto(listaAux.get(produtoQuantidade
								.getItensPedido().getId()).getQuantidadeProduto()
								+produtoQuantidade.getQuantidadeProduto());
				}else {
					listaAux.put(produtoQuantidade.getItensPedido().getId(), produtoQuantidade);
				}
			}
			
			return true;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.INSERE_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}

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

	public ArrayList<ProdutoQuantidade> listaProdutoVenda(Integer codigo) throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM itensPedido  where venda = ?;");
			pst.setInt(1, codigo);
			ResultSet rs = pst.executeQuery();
			ArrayList<ProdutoQuantidade> itensPedido = new ArrayList<ProdutoQuantidade>();
			while (rs.next()) {
				
				itensPedido.add(new ProdutoQuantidade(new ProdutoDao().consulta(rs.getInt("produtos")),
						rs.getInt("quantidade")));
			}
			
			return itensPedido;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.CONSULTA_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public ArrayList<Venda> pegaVendaDia() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("select * from Venda where day(?) = day(dataVenda);");
				pst.setDate(1,  java.sql.Date.valueOf(LocalDate.now()));
				ResultSet rs = pst.executeQuery();
				ArrayList<Venda> vendas = new ArrayList<>();
				while (rs.next()) {
					
					vendas.add( consultaCompleta(rs.getInt("id")));
				}
				return vendas;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public ArrayList<Venda> pegaVendaSemana() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("select * from Venda where week(?) = week(dataVenda); ");
				pst.setDate(1,  java.sql.Date.valueOf(LocalDate.now()));
				ResultSet rs = pst.executeQuery();
				ArrayList<Venda> vendas = new ArrayList<>();
				while (rs.next()) {
					
					vendas.add(consultaCompleta(rs.getInt("id")));
				}
				return vendas;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
	
	public ArrayList<Venda> pegaVendaMes() throws DaoException, ConexaoException {
		Connection conexao = Conexao.abreConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("select * from Venda where MONTH(?) = MONTH(dataVenda) ;");
				pst.setDate(1,  java.sql.Date.valueOf(LocalDate.now()));
				ResultSet rs = pst.executeQuery();
				ArrayList<Venda> vendas = new ArrayList<>();
				while (rs.next()) {
					
					vendas.add(consultaCompleta(rs.getInt("id")));
				}
				return vendas;
		} catch (Exception e) {
			throw new DaoException(EErrosDao.EXCLUI_DADO, e.getMessage(), this.getClass());
		} finally {
			Conexao.fechaConexao();
		}
	}
}
