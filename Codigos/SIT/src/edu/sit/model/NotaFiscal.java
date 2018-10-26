package edu.sit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


public class NotaFiscal implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Funcionario funcionario;
	private Cliente cliente;
	private ArrayList<Produto> produtos;
	private Calendar dataEmissao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Calendar getDataEmissao() {
		return dataEmissao;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	private NotaFiscal(Funcionario funcionario, Cliente cliente, ArrayList<Produto> produtos) {
	
		setCliente(cliente);
		setFuncionario(funcionario);
		setProdutos(produtos);
		this.dataEmissao = Calendar.getInstance();
	}
	public static NotaFiscal criaNotaFiscal (Funcionario funcionario, Cliente cliente, ArrayList<Produto> produtos) {
	
		return new NotaFiscal(funcionario,cliente,produtos);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaFiscal other = (NotaFiscal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
	


