package edu.sit.controller.cadastro;

import java.util.List;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.IDao;
import edu.sit.bancodedados.dao.InstaladorDao;
import edu.sit.erros.dao.DaoException;
import edu.sit.model.Usuario;

public class UsuarioDao extends InstaladorDao implements IDao<Usuario> {

	
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
	public Usuario consulta(Integer codigo) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> consultaTodos() throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> consultaVariosPorID(Integer... faixa) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insere(Usuario objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altera(Usuario objeto) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exclui(Integer... codigos) throws DaoException, ConexaoException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
