package edu.sit.model;

import java.util.ArrayList;

public class Venda {
	private Integer id;
	private Double valor;
	private Cliente cliente;
	private ArrayList<Produto> produtos;
	private Funcionario funcionario;
	private NotaFiscal notaFiscal;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
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
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
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
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	private Venda(Cliente cliente, ArrayList<Produto> produtos, Funcionario funcionario) {
			setCliente(cliente);
			setFuncionario(funcionario);
			setProdutos(produtos);
			
	}
	public Venda criaVenda(Cliente cliente, ArrayList<Produto> produtos, Funcionario funcionario) {
		return new Venda(cliente, produtos, funcionario);
	}
	
	
	
	@Override
	public String toString() {
		String listaProdutos = "";
		for (Produto produto : produtos) {
			listaProdutos += produto.toString()+"\n";
		}
		return "Funcionário:\t\t"+ getFuncionario()+ "\nCliente:\t\t"+ getCliente()+"\nValor:\t\t"+ getValor()+"\nProdutos:\t\t"+ listaProdutos;					
	}

	



}
