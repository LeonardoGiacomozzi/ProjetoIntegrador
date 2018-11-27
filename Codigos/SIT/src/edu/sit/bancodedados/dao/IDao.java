package edu.sit.bancodedados.dao;

import java.util.List;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.erros.dao.DaoException;

public interface IDao<T> {
	
	public abstract T consulta(Integer codigo) throws DaoException, ConexaoException;

	public abstract List<T> consultaTodos() throws DaoException, ConexaoException;

	public abstract List<T> consultaVariosPorID(Integer... faixa) throws DaoException, ConexaoException;

	public abstract boolean insere(T objeto) throws DaoException, ConexaoException;
	
	public abstract boolean altera(T objeto) throws DaoException, ConexaoException;
	
	public abstract boolean exclui(Integer... codigos) throws DaoException, ConexaoException;
	
}
