package edu.sit.model;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.sit.DataObject.ProdutoQuantidade;


public class Venda {
	private Integer id;
	private Double valor;
	private Integer clienteId;
	private Cliente cliente;
	private ArrayList<ProdutoQuantidade> produtos;
	private Integer funcionarioId;
	private Funcionario funcionario;
	private LocalDate dataVenda;
	
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


	public ArrayList<ProdutoQuantidade> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<ProdutoQuantidade> produtos) {
		this.produtos = produtos;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Integer funcionarioId) {
		this.funcionarioId = funcionarioId;
	}
	public LocalDate getDataVenda() {
		return dataVenda;
	}
	
	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Double getValorCompra() {
		double total = 0.0;
		for (ProdutoQuantidade itenPedido : getProdutos()) {

			total += itenPedido.getItensPedido().getValorUnitario()*itenPedido.getQuantidadeProduto();
		}
		return total;
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

	
	
	

	public Venda(Integer cliente,Integer funcionario) {
		setFuncionarioId(funcionario);
		setClienteId(cliente);
		setDataVenda(LocalDate.now());
	}
	
	public Venda(Integer clienteid,Integer funcionarioId, ArrayList<ProdutoQuantidade>produtos,Double valor,LocalDate dataVenda) {
		setValor(valor);
		setFuncionarioId(funcionarioId);
		setClienteId(clienteid);
		setProdutos(produtos);
		setDataVenda(dataVenda);
	}
	
	
	public Venda(Integer id,Integer clienteid,Integer funcionarioId,Double valor,LocalDate dataVenda) {
		setId(id);
		setValor(valor);
		setFuncionarioId(funcionarioId);
		setClienteId(clienteid);
		setDataVenda(dataVenda);
	}
	
	
	
	
	
	


	@Override
	public String toString() {
		String listaProdutos = "";
		for (ProdutoQuantidade produto : produtos) {
			listaProdutos += produto.getItensPedido().toString() + "\n";
		}
		return "Funcionário:\t\t" + getFuncionario() + "\nCliente:\t\t" + getCliente() + "\nValor:\t\t" + getValor()
				+ "\nProdutos:\t\t" + listaProdutos;
	}


}
