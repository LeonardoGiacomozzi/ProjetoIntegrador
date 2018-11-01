package edu.sit.bancodedados.dao;

import java.util.List;
import java.util.Map;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.model.Funcionario;

public class FuncionarioDao implements IDao<Funcionario>, IInstaladorDao {

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
	public Funcionario consulta(Integer codigo) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, Funcionario> consultaTodos() throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> consultaFaixa(Integer... faixa) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insere(Funcionario objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Funcionario> insereVarios(Map<Integer, Funcionario> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Funcionario> insereVarios(List<Funcionario> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insereVariosTransacao(List<Funcionario> objetos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altera(Funcionario objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exclui(Integer codigo) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exclui(Funcionario objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Funcionario pegaUltimoID(Integer idCliente) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
