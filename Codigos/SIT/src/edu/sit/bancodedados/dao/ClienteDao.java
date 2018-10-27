package edu.sit.bancodedados.dao;

import java.util.List;
import java.util.Map;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.model.Cliente;

public class ClienteDao implements IDao<Cliente>, IInstaladorDao {

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
	public Cliente consulta(Integer codigo) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
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
