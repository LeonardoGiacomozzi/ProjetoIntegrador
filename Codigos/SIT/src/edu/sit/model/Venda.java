package edu.sit.model;

import java.util.ArrayList;

import edu.sit.bancodedados.conexao.ConexaoException;
import edu.sit.bancodedados.dao.ProdutoDao;
import edu.sit.erros.dao.DaoException;

public class Venda {
	private Integer id;
	private Double valor;
	private Integer clienteId;
	private Cliente cliente;
	private ArrayList<Produto> produtos;
	private Integer funcionarioId;
	private Funcionario funcionario;

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

	public Double getValorCompra() {
		double total = 0.0;
		for (Produto produto : getProdutos()) {
			total += produto.getValorUnitario();
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

	private Venda(Cliente cliente, ArrayList<Produto> produtos, Funcionario funcionario) {
		setCliente(cliente);
		setFuncionario(funcionario);
		setProdutos(produtos);
	}
	
	private Venda(Integer id, Double valor, Integer funcionarioId, Integer clienteid,ArrayList<Integer>produtos) {
		setId(id);
		setValor(valor);
		setFuncionarioId(funcionarioId);
		setClienteId(clienteid);
		ArrayList<Produto> produtosNovo =  new ArrayList<Produto>();
		for (Integer produto : produtos) {
			try {
				produtosNovo.add(new ProdutoDao().consulta(produto));
			} catch (DaoException | ConexaoException e) {
				System.out.println(e.getMessage() + "\n\tErro ao gerar a venda");
			}
		}
		setProdutos(produtosNovo);
	}

	private Venda(Integer clienteid,Integer funcionarioId, ArrayList<Produto>produtos,Double valor) {
		setValor(valor);
		setFuncionarioId(funcionarioId);
		setClienteId(clienteid);
		setProdutos(produtos);
	}
	
	private Venda(Integer id,Integer clienteid,Integer funcionarioId,Double valor) {
		setId(id);
		setValor(valor);
		setFuncionarioId(funcionarioId);
		setClienteId(clienteid);
	}
	
	public static Venda criaVenda(Cliente cliente, ArrayList<Produto> produtos, Funcionario funcionario) {
		return new Venda(cliente, produtos, funcionario);
	}
	
	public static Venda criaVenda(Integer id,Integer cliente, Integer funcionario,Double valor) {
		return new Venda(id,cliente,funcionario,valor);
	}
	
	public static Venda criaVenda(Integer clienteid,Integer funcionarioId, ArrayList<Produto>produtos,Double valor)	{
		return new Venda(clienteid, funcionarioId, produtos, valor);
	}
	
	public static Venda consultaVendaBanco(Integer id, Double valor, Integer funcionarioId, Integer clienteid, ArrayList<Integer>produtos) {
		return new Venda(id, valor, funcionarioId, clienteid, produtos);
	}

	@Override
	public String toString() {
		String listaProdutos = "";
		for (Produto produto : produtos) {
			listaProdutos += produto.toString() + "\n";
		}
		return "Funcionário:\t\t" + getFuncionario() + "\nCliente:\t\t" + getCliente() + "\nValor:\t\t" + getValor()
				+ "\nProdutos:\t\t" + listaProdutos;
	}
}
