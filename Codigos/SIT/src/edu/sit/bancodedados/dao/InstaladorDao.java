package edu.sit.bancodedados.dao;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.erros.dao.DaoException;

public abstract class InstaladorDao {
	
	public abstract boolean criaTabela() throws DaoException, ConexaoException;
	
	public abstract boolean excluiTabela() throws DaoException, ConexaoException;
}
