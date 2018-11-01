package edu.sit.bancodedados.dao;

import java.util.List;
import java.util.Map;

import edu.sit.bancodedados.conexao.ConexaoException;

public interface IDao<T> {
	
	public abstract T consulta(Integer codigo) throws DaoException, ConexaoException;

	public abstract Map<Integer, T> consultaTodos() throws DaoException, ConexaoException;

	public abstract List<T> consultaFaixa(Integer... faixa) throws DaoException, ConexaoException;

	public abstract boolean insere(T objeto) throws DaoException, ConexaoException;

	public abstract List<T> insereVarios(Map<Integer, T> objetos) throws DaoException, ConexaoException;

	public abstract List<T> insereVarios(List<T> objetos) throws DaoException, ConexaoException;

	public abstract boolean insereVariosTransacao(List<T> objetos) throws DaoException, ConexaoException;
	
	public abstract boolean altera(T objeto) throws DaoException, ConexaoException;
	
	public abstract boolean exclui(Integer codigo) throws DaoException, ConexaoException;
	
	public abstract boolean exclui(T objeto) throws DaoException, ConexaoException;
	
	public abstract Integer pegaUltimoID() throws DaoException, ConexaoException;

}
