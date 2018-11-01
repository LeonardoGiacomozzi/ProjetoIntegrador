package edu.sit.bancodedados.dao;

import edu.sit.bancodedados.conexao.ConexaoException;

public interface IInstaladorDao {
	
	public abstract boolean criaTabela() throws DaoException, ConexaoException;
	
	public abstract boolean excluiTabela() throws DaoException, ConexaoException;
}
