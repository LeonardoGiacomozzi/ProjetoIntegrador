package edu.sit.bancodedados.dao;

import java.util.List;
import java.util.Map;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.model.Fornecedor;

public class FornecedorDao implements IDao<Fornecedor>, IInstaladorDao {

	@Override
	public boolean criaTabela() throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluiTabela() throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Fornecedor consulta(Integer codigo) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
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
	public List<Fornecedor> insereVarios(Map<Integer, Fornecedor> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
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
	public boolean exclui(Integer codigo) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exclui(Fornecedor objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Fornecedor pegaUltimoID(Integer idCliente) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
